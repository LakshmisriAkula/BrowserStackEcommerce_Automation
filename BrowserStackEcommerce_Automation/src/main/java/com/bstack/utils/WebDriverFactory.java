package com.bstack.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	public static WebDriver createDriver(String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			return new ChromeDriver();

		case "firefox":
			return new FirefoxDriver();

		case "edge":
			return new EdgeDriver();

		default:
			throw new RuntimeException("Unsupported browser: " + browser);

		}

	}
}
