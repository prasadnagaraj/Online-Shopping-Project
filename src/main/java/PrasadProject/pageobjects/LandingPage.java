package PrasadProject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PrasadProject.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
    //PageFactory	
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement passwordEle;

    @FindBy(id="login")
    WebElement submit;
    
    //No locator present in website
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    
    public ProductCatalogue loginApplication(String email,String password)
    {
    	userEmail.sendKeys(email);
    	passwordEle.sendKeys(password);
    	submit.click();
    	ProductCatalogue productCatalogue = new ProductCatalogue(driver);
    	return productCatalogue;
    }
    
    public void goTo()
    {
    	driver.get("https://www.rahulshettyacademy.com/client");
    }
    
    // not element present in website
    public String getErrorMeaasge()
    {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    }

}