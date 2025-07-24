package com.bstack.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(css = ".buy-btn")
    WebElement checkoutButton;
    
    @FindBy(id = "firstNameInput")
    WebElement firstName;

    @FindBy(id = "lastNameInput")
    WebElement lastName;

    @FindBy(id = "addressLine1Input")
    WebElement address;

    @FindBy(id = "provinceInput")
    WebElement state;

    @FindBy(id = "postCodeInput")
    WebElement postalCode;

    @FindBy(id = "checkout-shipping-continue")
    WebElement submitButton;

    @FindBy(id = "confirmation-message")
    WebElement orderConfirmation;
    
    @FindBy(xpath = "//span[@class='bag bag--float-cart-closed']")
    public static WebElement cartButton;
    
  

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public void fillShippingFormAndSubmit(String fname, String lname, String addr, String st, String pin) {
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        address.sendKeys(addr);
        state.sendKeys(st);
        postalCode.sendKeys(pin);
        submitButton.click();
    }
    
    public boolean isOrderConfirmed() {
        return orderConfirmation.isDisplayed();
    }
}
