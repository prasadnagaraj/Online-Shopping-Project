package PrasadProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PrasadProject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
    //PageFactory	
    @FindBy(css=".cartSection h3")
    List<WebElement> productTitles;
    
   
    @FindBy(css=".totalRow button")
    WebElement checkoutEle;
   
    
   public Boolean verifyProductDisplay(String productName)
   {
	   Boolean match = productTitles.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
	   return match;
   }
    
  public CheckoutPage goToCheckout()
  {
	  checkoutEle.click();
	  CheckoutPage checkoutPage = new CheckoutPage(driver);
	  return checkoutPage;
  }
       
}
