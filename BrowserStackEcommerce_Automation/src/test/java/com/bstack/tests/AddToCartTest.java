package com.bstack.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bstack.base.TestBase;
import com.bstack.pages.CartPage;
import com.bstack.pages.LoginPage;
import com.bstack.pages.ProductPage;
import com.bstack.utils.WaitUtils;

public class AddToCartTest extends TestBase {
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;

	@BeforeMethod
	public void initPagesAndLogin() throws InterruptedException {

		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);

		loginPage.signInLink.click();

		WaitUtils.waitForVisibility(driver, loginPage.usernameFieldLocator, 10);

		loginPage.login("demouser", "testingisfun99");

		Thread.sleep(3000);

	}

	@Test(priority = 1)
	public void addSingleItemToCartTest() throws InterruptedException {
		productPage.addFirstProductToCart();
		WaitUtils.waitForCartCount(driver, 1);
		Assert.assertEquals(cartPage.getCartItemCount(), "1");
		logWithScreenshot("Single item added to cart");

	}

	@Test(priority = 2)
	public void addMultipleItemsToCartTest() throws InterruptedException {
		productPage.addMultipleProductsToCart(3);
		WaitUtils.waitForCartCount(driver, 3);
		Assert.assertEquals(cartPage.getCartItemCount(), "3");
		logWithScreenshot("Multiple items added to cart");

	}

	@Test(priority = 3)
	public void removeItemFromCartTest() throws InterruptedException {
		productPage.addMultipleProductsToCart(2);
		WaitUtils.waitForCartCount(driver, 2);
		int before = cartPage.getTotalItemsInCart();
		logWithScreenshot("2 items added to cart");
		cartPage.removeFirstItemFromCart();
		WaitUtils.waitForCartCount(driver, before - 1);
		logWithScreenshot("1 item removed from the cart");
		int after = cartPage.getTotalItemsInCart();
		Assert.assertEquals(after, before - 1);
		logWithScreenshot("1 item left in the cart");

	}
}
