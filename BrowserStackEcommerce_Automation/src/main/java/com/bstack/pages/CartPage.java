package com.bstack.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    @FindBy(css = "span.bag__quantity")
    WebElement cartCount;
    
    @FindBy(css = ".float-cart__shelf-container > div")
    List<WebElement> cartItems;

    @FindBy(css = ".shelf-item__del")
    List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCartItemCount() {
        return cartCount.getText(); 
    }

    public int getTotalItemsInCart() {
        return cartItems.size();
    }

    public void removeFirstItemFromCart() {
        if (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
        }
    }
}
