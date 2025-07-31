package com.bstack.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	WebDriver driver;

	private By cartCount = By.cssSelector(".bag__quantity");
	private By cartItems = By.cssSelector(".float-cart__shelf-container > div");
	private By removeButtons = By.cssSelector(".shelf-item__del");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getCartItemCount() {
		return driver.findElement(cartCount).getText();
	}

	public int getTotalItemsInCart() {
		return driver.findElements(cartItems).size();
	}

	public void removeFirstItemFromCart() {
		List<WebElement> buttons = driver.findElements(removeButtons);
		if (!buttons.isEmpty()) {
			buttons.get(0).click();
		}
	}
}
