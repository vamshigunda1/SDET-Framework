package com.sdet.framework.pages;

import com.sdet.framework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * DashboardPage - Page Object Model for dashboard page
 */
public class DashboardPage extends WebDriverUtils {

    // Locators
    private final By pageTitle = By.className("title");
    private final By logoutButton = By.id("logout_sidebar_link");
    private final By productList = By.className("inventory_list");
    private final By userMenu = By.id("user_menu");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return getText(pageTitle);
    }

    /**
     * Check if product list is visible
     */
    public boolean isProductListVisible() {
        return isElementVisible(productList);
    }

    /**
     * Click logout button
     */
    public void clickLogout() {
        click(logoutButton);
    }

    /**
     * Check if user is logged in (by checking user menu presence)
     */
    public boolean isUserLoggedIn() {
        return isElementVisible(userMenu);
    }
}
