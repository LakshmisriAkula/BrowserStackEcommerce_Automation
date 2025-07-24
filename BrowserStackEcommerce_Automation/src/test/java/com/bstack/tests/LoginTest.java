package com.bstack.tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bstack.base.TestBase;
import com.bstack.pages.LoginPage;
import com.bstack.utils.ConfigReader;
import com.bstack.utils.WaitUtils;

public class LoginTest extends TestBase {
	String sheetName = "Logindata";

	@Test(dataProvider = "LoginData")
	public void loginTestCases(String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.signInLink.click();

		WaitUtils.waitForVisibility(driver, loginPage.usernameFieldLocator, 10);

		loginPage.login(username + Keys.ENTER, password + Keys.ENTER);

		Thread.sleep(5000);

		Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");
		
		logWithScreenshot("Signin Successful");

	}

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws Exception {
		Object data[][] = ConfigReader.getTestData(sheetName);

		return data;
	}

}
