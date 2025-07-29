package com.bstack.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bstack.base.TestBase;
import com.bstack.pages.LoginPage;
import com.bstack.utils.ConfigReader;

public class LoginTest extends TestBase {
	String sheetName = "Logindata";

	@Test(dataProvider = "LoginData")
	public void loginTestCases(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);

		driver.findElement(loginPage.signInLink).click();

		loginPage.login(username, password);

		Assert.assertTrue(loginPage.isLoginSuccessful(),
				"Login failed with username: '\" + username + \"' and password: '\" + password + \"'");

		logWithScreenshot("Signin Successful with username: '" + username + "' and password: '******'");
	}

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws Exception {
		Object data[][] = ConfigReader.getTestData(sheetName);

		return data;
	}

}
