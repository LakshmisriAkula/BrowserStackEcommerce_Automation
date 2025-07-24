package com.bstack.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
    public static WebElement waitForVisibility(WebDriver driver, By element, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    
    public static void waitForCartCount(WebDriver driver, int expectedCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            String countText = d.findElement(By.className("cart-count")).getText();
            try {
                return Integer.parseInt(countText) == expectedCount;
            } catch (NumberFormatException e) {
                return false;
            }
        });
    }

    
	public static String captureScreenshot(WebDriver driver, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String path = "screenshots/" + testName + "_" + timestamp + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		try {
			Files.copy(src.toPath(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}