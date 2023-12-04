package PrasadProject.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import PrasadProject.pageobjects.LandingPage;
import PrasadProject.pageobjects.ProductCatalogue;
import PrasadProject.TestComponents.BaseTest;
import PrasadProject.TestComponents.Retry;
import PrasadProject.pageobjects.CartPage;
import PrasadProject.pageobjects.CheckoutPage;
import PrasadProject.pageobjects.ConfirmationPage;
import dev.failsafe.internal.util.Assert;
    

public class ErrorValidationsTest extends BaseTest
{
	
	@Test(groups = {"ErrorHandling"} , retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		 //String productName = "ZARA COAT 3";
         ProductCatalogue productCatalogue = landingPage.loginApplication("Abbaajbab","Hanjfjk022");
         //System.out.println("Incorrect Login Details Passsed");
         //AssertJUnit.assertEquals("Incorrect email  password.",landingPage.getErrorMeaasge());
        
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		 String productName = "ZARA COAT 3";
         ProductCatalogue productCatalogue = landingPage.loginApplication("prasadnagaraj991@gmail.com","Hanikha@2022");
         
         
         List<WebElement> products = productCatalogue.getProductList();
         productCatalogue.addProductToCart(productName);
         CartPage cartPage = productCatalogue.goToCartPage();
         
         
         Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
         AssertJUnit.assertFalse(match);
      
	}    

}