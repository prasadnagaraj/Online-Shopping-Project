package PrasadProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import PrasadProject.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
    //PageFactory	
    
    @FindBy(css="[placeholder='Select Country']")
    WebElement country;
   
    @FindBy(xpath="//section[contains(@class,'ta-results')]/button[2]")
    WebElement selectCountry;
    
    @FindBy(css=".action__submit")
    WebElement submit;
    
    By results = By.cssSelector(".ta-results");
    By scroll = By.cssSelector(".action__submit");
    
    
    public void selectCountry(String countryName)
    {
    	 Actions a= new Actions(driver);
         a.sendKeys(country,countryName).build().perform();
         waitForElementToAppear(results);
         selectCountry.click();
    }
    
   public ConfirmationPage submitOrder() throws InterruptedException
   {
	   waitForElementToDisappear();
       ((JavascriptExecutor) driver).executeScript("scroll(200,300)");
       waitForElementToAppear(scroll);
       submit.click();
       ConfirmationPage confirmationPage = new ConfirmationPage(driver);
       return confirmationPage;
       
       
   }
       
}
