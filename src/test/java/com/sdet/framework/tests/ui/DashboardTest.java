package com.sdet.framework.tests.ui;

import com.sdet.framework.core.BaseTest;
import com.sdet.framework.pages.DashboardPage;
import com.sdet.framework.pages.LoginPage;
import com.sdet.framework.utils.ConfigReader;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * DashboardTest - Sample test for dashboard functionality
 */
public class DashboardTest extends BaseTest {

    @Test(description = "Verify dashboard page after login")
    public void testDashboardPageDisplay() {
        driver.navigate().to(ConfigReader.getApplicationUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user@saucedemo.com", "secret_sauce");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isProductListVisible(), "Product list should be visible");
        logger.info("Dashboard page verified");
    }

    @Test(description = "Verify logout functionality")
    public void testLogoutFunctionality() {
        driver.navigate().to(ConfigReader.getApplicationUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user@saucedemo.com", "secret_sauce");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        dashboardPage.clickLogout();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "Should be redirected to login page");
        logger.info("Logout test passed");
    }
}
