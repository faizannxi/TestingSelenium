package rahulshetty.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".hero-primary")
	WebElement text;
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement SelectCountry;
	
//	By result = By.cssSelector(".ta-result");
	
	public void selectCountry(String count) {
		Actions a = new Actions(driver);
		a.sendKeys(country, count).build()
				.perform();
		SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
