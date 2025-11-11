package com.sdet.framework.pages;

import com.sdet.framework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage - Example Page Object Model class
 */
public class LoginPage extends WebDriverUtils {

    // Locators
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private final By errorMessage = By.className("error-message");
    private final By pageHeader = By.tagName("h1");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter email
     */
    public void enterEmail(String email) {
        sendText(emailInput, email);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        sendText(passwordInput, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Perform login
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Get page header
     */
    public String getPageHeader() {
        return getText(pageHeader);
    }

    /**
     * Check if login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        return isElementVisible(loginButton);
    }
}
