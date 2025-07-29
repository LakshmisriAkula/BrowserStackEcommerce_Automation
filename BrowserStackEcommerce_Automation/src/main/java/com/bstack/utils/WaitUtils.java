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
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public static WebElement waitForVisibility(WebDriver driver, By element, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static void waitForCartCount(WebDriver driver, int expectedCount) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By cartCount = By.cssSelector(".bag__quantity");

		wait.until(ExpectedConditions.presenceOfElementLocated(cartCount));
		wait.until(ExpectedConditions.textToBe(cartCount, String.valueOf(expectedCount)));
	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		String path = "screenshots/" + testName + "_" + timestamp + ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}