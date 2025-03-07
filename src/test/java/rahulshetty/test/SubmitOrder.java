package rahulshetty.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import rahulshetty.TestComponent.BaseTest;
import rahulshetty.pageObject.CartPage;
import rahulshetty.pageObject.CheckoutPage;
import rahulshetty.pageObject.ConfirmationPage;
import rahulshetty.pageObject.LandingPageDetails;
import rahulshetty.pageObject.OrderPage;
import rahulshetty.pageObject.ProductCatalog;

public class SubmitOrder extends BaseTest {
	String productName = "ZARA COAT 3";
	LandingPageDetails la;
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		la = launchApplication();
		ProductCatalog pc = la.loginApplication(input.get("email"), input.get("password"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> product = pc.getProductList();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		pc.addProductToCart(productName);
		js.executeScript("window.scrollTo(0, 0)");
		CartPage cp = pc.goToCartPage();
		//HEllo there
		boolean match = cp.VerifyProductDisplay(productName);
		Assert.assertTrue(match, "Product not found in cart!");
		js.executeScript("window.scrollBy(0,300)");
		CheckoutPage checkoutPage = cp.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirm = checkoutPage.submitOrder();
		String confirmationText = confirm.verifyConfirmation();
		System.out.println("Order Confirmation: " + confirmationText);
		Assert.assertTrue(confirmationText.contains("Thank you"), "Order confirmation failed!");
	}
	
	
	@Test(dependsOnMethods="submitOrder", groups= {"Purchase"})
	public void OrderHistoryTest() {
		ProductCatalog productCatalog = la.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	public void takeScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File("C:\\Users\\Hi\\eclipse\\java-2024-12\\eclipse");
		FileUtils.copyFile(Source, file);
		
	}
	
	
//	@DataProvider
//	public Object[][] getData() throws IOException
//	{
//
//		
//		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
//		return new Object[][]  {{data.get(0)}, {data.get(1) } };
//		
//	}
	
	 @DataProvider
	  public Object[][] getData()
	  {
		 HashMap<String,String> map = new HashMap<String,String>();
			map.put("email", "anshika@gmail.com");
			map.put("password", "Iamking@000");
			map.put("product", "ZARA COAT 3");
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email", "shetty@gmail.com");
			map1.put("password", "Iamking@000");
			map1.put("product", "ADIDAS ORIGINAL");
	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
	    
	  }
	

}
