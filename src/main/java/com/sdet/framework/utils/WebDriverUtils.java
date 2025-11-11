package com.sdet.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * WebDriverUtils class with common WebDriver operations
 */
public class WebDriverUtils {

    private static final Logger logger = LogManager.getLogger(WebDriverUtils.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    /**
     * Navigate to URL
     */
    public void navigateToUrl(String url) {
        try {
            driver.navigate().to(url);
            logger.info("Navigated to URL: " + url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: " + url + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible and click
     */
    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element and send text
     */
    public void sendText(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.info("Sent text '" + text + "' to element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to send text to element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            logger.info("Retrieved text from element: " + locator + " - " + text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible
     */
    public boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element is visible: " + locator);
            return true;
        } catch (Exception e) {
            logger.error("Element not visible: " + locator);
            return false;
        }
    }

    /**
     * Select dropdown by visible text
     */
    public void selectDropdownByText(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(text);
            logger.info("Selected dropdown option: " + text);
        } catch (Exception e) {
            logger.error("Failed to select dropdown: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Select dropdown by value
     */
    public void selectDropdownByValue(By locator, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.selectByValue(value);
            logger.info("Selected dropdown value: " + value);
        } catch (Exception e) {
            logger.error("Failed to select dropdown: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Wait for page to load
     */
    public void waitForPageLoad() {
        try {
            wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
            logger.info("Page loaded successfully");
        } catch (Exception e) {
            logger.warn("Page load wait timeout: " + e.getMessage());
        }
    }

    /**
     * Execute JavaScript
     */
    public Object executeScript(String script, Object... args) {
        try {
            return ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script, args);
        } catch (Exception e) {
            logger.error("Failed to execute script: " + e.getMessage());
            throw e;
        }
    }
}
