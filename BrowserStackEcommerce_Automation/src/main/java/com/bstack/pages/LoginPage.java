package com.bstack.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;

	@FindBy(id = "signin")
	public WebElement signInLink ;

//	@FindBy(xpath="//div[contains(text(),'Select Username')]")
//	public WebElement usernameField;
	
	@FindBy(id = "username")
	private WebElement usernameFieldInput;

		
	@FindBy(xpath="//div[contains(text(),'Select Password')]")
	private WebElement passwordFieldInput;

	@FindBy(xpath = "//button[@id='login-btn']")
	private WebElement loginButton;

	@FindBy(xpath = "//span[@class='username']")
	private WebElement loggedInUserName;
	
    public By usernameFieldLocator = By.id("username"); // For WaitUtils in test


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}


	public void login(String username, String password) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.elementToBeClickable(usernameFieldInput)).sendKeys(username);
	    wait.until(ExpectedConditions.elementToBeClickable(passwordFieldInput)).sendKeys(password);
	    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	public boolean isLoginSuccessful() {
		return loggedInUserName.isDisplayed();
	}
}
