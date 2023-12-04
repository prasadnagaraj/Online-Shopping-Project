package PrasadProject.Tests;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import dev.failsafe.internal.util.Assert;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException 
	{
		String productName = "ADIDAS ORIGINAL";
		  FirefoxOptions fo = new FirefoxOptions();
        //ChromeOptions co = new ChromeOptions(); 
		fo.setBrowserVersion("119");
		 WebDriver driver = new FirefoxDriver(fo);
        // WebDriver driver = new ChromeDriver();
         
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         driver.manage().window().maximize();
         driver.get("https://www.rahulshettyacademy.com/client");
         driver.findElement(By.id("userEmail")).sendKeys("prasadnagaraj991@gmail.com");
         driver.findElement(By.id("userPassword")).sendKeys("Hanikha@2022");
         driver.findElement(By.id("login")).click();
         
         WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
         
         List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
         WebElement prod = products.stream().filter(product->
         product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
         
         prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//         for(int i=0;i<products.size();i++) 
//         {
//        	 String name = products.get(i).getText();
//        	 if(name.contains(productName))
//        	 {
//        		 driver.findElements(By.xpath("//div/div/button[2]")).get(i).click();
//        		 break;
//        	 }
//         }
         
         //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
         //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
         Thread.sleep(3000);
         driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
         
         List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
         Boolean match = cartProducts.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
         Assert.isTrue(match, productName, args);
         driver.findElement(By.cssSelector(".totalRow button")).click();
         
         Actions a= new Actions(driver);
         a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
         //a[contains(@class,'action__submit')]
         //"(//button[contains(@class,'ta-item')])[2]"
         driver.findElement(By.xpath("//section[contains(@class,'ta-results')]/button[2]")).click();
         
         Thread.sleep(3000);
        
         ((JavascriptExecutor) driver).executeScript("scroll(200,500)");
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
         driver.findElement(By.cssSelector(".action__submit")).click();
         
         String messageName = driver.findElement(By.cssSelector(".hero-primary")).getText();
         AssertJUnit.assertEquals(messageName,"THANKYOU FOR THE ORDER.");
        
         AssertJUnit.assertTrue(messageName.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
         
         
         
         
	}

}
