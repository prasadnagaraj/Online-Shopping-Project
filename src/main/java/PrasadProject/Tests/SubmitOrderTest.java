package PrasadProject.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PrasadProject.pageobjects.LandingPage;
import PrasadProject.pageobjects.OrderPage;
import PrasadProject.pageobjects.ProductCatalogue;
import PrasadProject.TestComponents.BaseTest;
import PrasadProject.pageobjects.CartPage;
import PrasadProject.pageobjects.CheckoutPage;
import PrasadProject.pageobjects.ConfirmationPage;
import dev.failsafe.internal.util.Assert;
    

public class SubmitOrderTest extends BaseTest
{
	 String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups ="purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
         ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
         
         
         List<WebElement> products = productCatalogue.getProductList();
         productCatalogue.addProductToCart(input.get("product"));
         CartPage cartPage = productCatalogue.goToCartPage();
         
         
         Boolean match = cartPage.verifyProductDisplay(input.get("product"));
         //Assert.assertTrue(match);
         CheckoutPage checkoutPage = cartPage.goToCheckout();
         checkoutPage.selectCountry("india");
         ConfirmationPage confirmationPage = checkoutPage.submitOrder();
         String confirmMessage = confirmationPage.getConfirmationMessage();
         AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
           
	}
	
	//To Verify ZARA COAT 3 is displaying in orders or not
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		 ProductCatalogue productCatalogue = landingPage.loginApplication("prasadnagaraj991@gmail.com","Hanikha@2022");
		 OrderPage orderPage = productCatalogue.goToOrderPage();
		 AssertJUnit.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		 List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//main//java//PrasadProject//data//PurchaseOrder.json");
         return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "vijiprasad@gmail.com");
//	map.put("password", "Viji@2022");
//	map.put("product", "ADIDAS ORIGINAL");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "rojasabari@gmail.com");
//	map1.put("password", "Maanvik@2022");
//	map1.put("product", "IPHONE 13 PRO");
	
//  return new Object[][] {{map},{map1}};	
	
	
	
	
	
	

}