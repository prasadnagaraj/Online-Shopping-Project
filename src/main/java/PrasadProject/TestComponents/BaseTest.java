package PrasadProject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PrasadProject.pageobjects.LandingPage;

public class BaseTest
{
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initialization() throws IOException
	{
		
		// Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//PrasadProject//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		String browserVersion = prop.getProperty("version");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions co = new ChromeOptions();
			co.setBrowserVersion("browserVersion");
            //co.addArguments("headless");    //runs in headless mode
		    driver = new ChromeDriver(co); 
		    // driver.manage().window().setSize(new Dimension(1400,900));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			

		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions fo = new FirefoxOptions();
			fo.setBrowserVersion("browserVersion");
			
		    driver = new FirefoxDriver(fo);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;	
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);    
		
		//string to HashMap -  MvMReposittory jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		
		return data;
	}
	
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File destination =new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
				
	}

	
	@BeforeMethod(alwaysRun =true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initialization();
	    landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
	}
	
	@AfterMethod(alwaysRun =true)
	public void teadDown()
	{
		driver.close();
	}
	

}
