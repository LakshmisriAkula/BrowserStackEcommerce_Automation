package com.bstack.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.bstack.utils.ConfigReader;
import com.bstack.utils.WebDriverFactory;

public class TestBase {
	  public WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        driver = WebDriverFactory.createDriver(ConfigReader.get("browser"));
	        driver.get(ConfigReader.get("url"));
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}
