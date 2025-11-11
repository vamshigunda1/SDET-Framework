package com.sdet.framework.tests.ui;

import com.sdet.framework.core.BaseTest;
import com.sdet.framework.pages.LoginPage;
import com.sdet.framework.utils.ConfigReader;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * LoginTest - Sample UI test for login functionality
 */
public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        driver.navigate().to(ConfigReader.getApplicationUrl());
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user@saucedemo.com", "secret_sauce");
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login"), "User should not be on login page");
        logger.info("Login test passed");
    }

    @Test(description = "Verify login with invalid password shows error")
    public void testLoginWithInvalidPassword() {
        driver.navigate().to(ConfigReader.getApplicationUrl());
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user@saucedemo.com", "invalid_password");
        
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMessage, "Error message should be displayed");
        Assert.assertTrue(errorMessage.contains("do not match"), "Error message should contain 'do not match'");
        logger.info("Invalid password test passed");
    }

    @Test(description = "Verify login page elements are visible")
    public void testLoginPageElementsVisible() {
        driver.navigate().to(ConfigReader.getApplicationUrl());
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");
        logger.info("Login page elements visibility test passed");
    }
}
