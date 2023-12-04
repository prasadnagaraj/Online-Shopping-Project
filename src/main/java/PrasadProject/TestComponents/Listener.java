package PrasadProject.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PrasadProject.resources.ExtentReporterNG;

public class Listener extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique test id
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		//test.log(Status.FAIL, "Test Passed");
		extentTest.get().fail(result.getThrowable());
		
		try
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} 
	    catch (Exception e1) 
		{
           e1.printStackTrace();
	    } 
		
		try {
			String filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ScreenShot and attach a report

		String filePath = null;
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		
	}
	public String getScreenshot(String methodName, WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	
	@Override
	public void onStart(ITestContext context)
	{
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	
	
	

}
