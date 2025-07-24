package com.bstack.tests;

import com.bstack.base.TestBase;
import com.bstack.pages.CartPage;
import com.bstack.pages.CheckoutPage;
import com.bstack.pages.LoginPage;
import com.bstack.pages.ProductPage;
import com.bstack.utils.WaitUtils;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckOutTest extends TestBase {
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;

	@BeforeMethod
	public void initPagesAndLogin() throws InterruptedException {
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);

		loginPage.signInLink.click();

		WaitUtils.waitForVisibility(driver, loginPage.usernameFieldLocator, 10);

		loginPage.login("demouser", "testingisfun99");

		Thread.sleep(3000);

	}

	@Test(priority = 1)
	public void placeOrderWithValidDetails() throws InterruptedException {
		productPage.addFirstProductToCart();
		WaitUtils.waitForCartCount(driver, 1);
		checkoutPage.clickCheckout();
		checkoutPage.fillShippingFormAndSubmit("Chandu", "Kola", "MVP", "AP", "530017");
		Thread.sleep(2000);
		Assert.assertTrue(checkoutPage.isOrderConfirmed(), "Order confirmation not displayed!");
		logWithScreenshot("Order Confirmed");

	}

	@Test(priority = 2)
	public void checkoutWithoutItemsNegativeTest() throws InterruptedException {
		CheckoutPage.cartButton.click();

		String emptyCartMessage = driver.findElement(By.xpath("//p[@class='shelf-empty']")).getText();

//		checkoutPage.clickCheckout();

		Assert.assertTrue(emptyCartMessage.contains("Add some products in the bag"),
				"Checkout should not succeed without items!");
		
		logWithScreenshot("Cart is empty and Add some products in the bag displayed.");

	}
}
