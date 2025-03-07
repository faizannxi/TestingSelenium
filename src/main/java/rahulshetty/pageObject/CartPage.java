package rahulshetty.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productlist;
	
	@FindBy(css=".totalRow button")
	WebElement eveButton;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = productlist.stream().anyMatch(head -> head.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		eveButton.click();
		return new CheckoutPage(driver);
		
	}

}
