package rahulshetty.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.TestComponent.BaseTest;
import rahulshetty.pageObject.CartPage;
import rahulshetty.pageObject.CheckoutPage;
import rahulshetty.pageObject.ConfirmationPage;
import rahulshetty.pageObject.LandingPageDetails;
import rahulshetty.pageObject.ProductCatalog;

public class standDown {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().driverVersion("133.0.6943.142").setup();
//		System.setProperty("webdriver.chrome.driver", "C:/Users/Hi/Documents/chrome/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";
		LandingPageDetails la = new LandingPageDetails(driver);
		la.goTo();
		ProductCatalog pc = la.loginApplication("anshika@gmail.com", "Iamking@000");
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
		Boolean match = cp.VerifyProductDisplay(productName);
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		CheckoutPage CheckoutPage = cp.goToCheckout();
		CheckoutPage.selectCountry("india");
		ConfirmationPage confirm = CheckoutPage.submitOrder();
		String text = confirm.verifyConfirmation();
		System.out.println(text);
	}
}
