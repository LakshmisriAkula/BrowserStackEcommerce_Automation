package com.bstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bstack.utils.WaitUtils;

public class LoginPage {
	WebDriver driver;

	public By signInLink = By.id("signin");

	public By userNameDropdown = By
			.xpath("//div[@id='username']//div[contains(@class,'css-tlfecz-indicatorContainer')]");

	public By passwordDropdown = By
			.xpath("//div[@id='password']//div[contains(@class,'css-tlfecz-indicatorContainer')]");

	private By loginButton = By.xpath("//button[@id='login-btn']");

	public By loggedInUserName = By.xpath("//span[@class='username']");

	public By usernameFieldLocator = By.id("username");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String username, String password) {

		driver.findElement(userNameDropdown).click();

		By usernameOption = By.xpath("//div[@id='username']//div[text()='" + username + "']");

		WaitUtils.waitForVisibility(driver, usernameOption, 10);

		driver.findElement(usernameOption).click();

		driver.findElement(passwordDropdown).click();

		By passwordOption = By.xpath("//div[@id='password']//div[text()='" + password + "']");

		WaitUtils.waitForVisibility(driver, passwordOption, 10);

		driver.findElement(passwordOption).click();

		driver.findElement(loginButton).click();
	}

	public boolean isLoginSuccessful() {

		WaitUtils.waitForVisibility(driver, loggedInUserName, 10);
		
		return driver.findElement(loggedInUserName).isDisplayed();
	}
}
