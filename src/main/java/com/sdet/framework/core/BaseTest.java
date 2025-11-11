package com.sdet.framework.core;

import com.sdet.framework.drivers.DriverFactory;
import com.sdet.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest class - Parent class for all test classes
 * Handles WebDriver initialization and cleanup
 */
public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        driver = DriverFactory.initializeDriver(browser);
        driver.manage().window().maximize();
        logger.info("Test setup completed");
    }

    @AfterMethod
    public void tearDown() {
        if (DriverFactory.isDriverInitialized()) {
            DriverFactory.quitDriver();
            logger.info("Test teardown completed");
        }
    }

    /**
     * Get WebDriver instance
     */
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
