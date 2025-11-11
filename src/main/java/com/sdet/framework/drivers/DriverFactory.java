package com.sdet.framework.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DriverFactory class to manage WebDriver instances with ThreadLocal
 * Supports Chrome, Firefox, and Edge browsers
 */
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type
     */
    public static WebDriver initializeDriver(String browserName) {
        if (driver.get() != null) {
            return driver.get();
        }

        WebDriver webDriver = null;
        browserName = browserName.toLowerCase().trim();

        switch (browserName) {
            case "chrome" -> webDriver = initializeChrome();
            case "firefox" -> webDriver = initializeFirefox();
            case "edge" -> webDriver = initializeEdge();
            default -> {
                logger.error("Browser not supported: " + browserName);
                throw new IllegalArgumentException("Browser not supported: " + browserName);
            }
        }

        driver.set(webDriver);
        logger.info("WebDriver initialized for: " + browserName);
        return webDriver;
    }

    /**
     * Initialize Chrome browser
     */
    private static WebDriver initializeChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(options);
    }

    /**
     * Initialize Firefox browser
     */
    private static WebDriver initializeFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        return new FirefoxDriver(options);
    }

    /**
     * Initialize Edge browser
     */
    private static WebDriver initializeEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    /**
     * Get the WebDriver instance from ThreadLocal
     */
    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            logger.warn("WebDriver is not initialized");
        }
        return webDriver;
    }

    /**
     * Quit the WebDriver and remove from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            logger.info("WebDriver closed successfully");
            driver.remove();
        }
    }

    /**
     * Check if WebDriver is initialized
     */
    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }
}
