package com.bstack.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bstack.utils.ConfigReader;
import com.bstack.utils.WaitUtils;
import com.bstack.utils.WebDriverFactory;

public class TestBase {
	public static ExtentTest logger;
	  public static WebDriver driver;

	    @BeforeMethod
	    @Parameters("browser")
	    public void setUp(String browser) {
	        driver = WebDriverFactory.createDriver(browser);
	        driver.manage().window().maximize();
			driver.get("https://bstackdemo.com");

	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	    
		protected void logWithScreenshot(String message) {
			String path = WaitUtils.captureScreenshot(driver, message.replace(" ", "_"));
			if (logger != null) {
				logger.log(Status.INFO, message).addScreenCaptureFromPath(path);
			} else {
				System.out.println("⚠️ Logger is null. Screenshot taken: " + path);
			}
		}
	}
