package rahulshetty.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> product = driver.findElements(By.cssSelector(".card"));
	@FindBy(css = ".card")
	List<WebElement> products;
	By products1 = By.cssSelector(".card");
	By addtoCart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductList() {
		waitForElementToAppear(products1);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream()
				.filter(producta -> producta.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prode = getProductByName(productName);
		prode.findElement(addtoCart).click();
	}
	
	public void cart() {
		
	}

}
