package rahulshetty.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshetty.TestComponent.BaseTest;
import rahulshetty.pageObject.CartPage;
import rahulshetty.pageObject.LandingPageDetails;
import rahulshetty.pageObject.ProductCatalog;

public class ErrorValidations extends BaseTest {
	
	@Test(groups={"ErrorHandling"})
	public void validateErrorMessage() throws IOException {
		landingPage.loginApplication("anshika@gmail.com", "Iamki000");
		Assert.assertEquals("Incorrect email or password.", landingPage.ErrorMessage());
	}
	
	@Test
	public void ProductValueValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog pc = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		Thread.sleep(3000);
		List<WebElement> product = pc.getProductList();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(4000);
		pc.addProductToCart(productName);
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, 0)");
		Thread.sleep(3000);
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.VerifyProductDisplay("ZARA COAT 3");
	}
}
