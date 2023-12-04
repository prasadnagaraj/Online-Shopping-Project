package PrasadProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PrasadProject.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent
{
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
    //PageFactory	
    @FindBy(xpath="//tr/td[2]")
    List<WebElement> productNames;
    
   
    @FindBy(css=".totalRow button")
    WebElement checkoutEle;
   
    
   public Boolean verifyOrderDisplay(String productName)
   {
	   Boolean match = productNames.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
	   return match;
   }
    
  
       
}
