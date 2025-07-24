package com.bstack.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    WebDriver driver;

    @FindBy(css = ".shelf-item__buy-btn")
    public List<WebElement> addToCartButtons;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addFirstProductToCart() {
        addToCartButtons.get(0).click();
    }

    public void addMultipleProductsToCart(int count) {
        for (int i = 0; i < count && i < addToCartButtons.size(); i++) {
        	System.out.println(addToCartButtons.size());
            addToCartButtons.get(i).click();
        }
    }
}
