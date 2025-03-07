package rahulshetty.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;

public class LandingPageDetails extends AbstractComponent{
	WebDriver driver;
	public LandingPageDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
//	WebElement useremail =	driver.findElement(By.id("userEmail"));
	
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(className=".ng-trigger-flyInOut")
	WebElement error;
	
	public ProductCatalog loginApplication(String email, String password) {
		
		useremail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
		
	}
	
	public String ErrorMessage() {
		waitForElementToAppear(error);
		return error.getText();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
