package com.sdet.framework.tests.ui;

import com.sdet.framework.core.BaseTest;
import com.sdet.framework.drivers.DriverFactory;
import com.sdet.framework.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * HealeniumDemoTest
 *
 * Demonstrates Healenium self-healing in two steps:
 * 1) baselineStoreUsernameLocator: uses a correct locator to store baseline (persisted by Healenium backend)
 * 2) healUsernameLocatorAfterChange: uses an intentionally broken locator; Healenium should heal and find the element
 *
 * Pre-req: start backend with `docker compose up -d` so history is persisted.
 */
public class HealeniumDemoTest extends BaseTest {

    private static final String LOGIN_URL = ConfigReader.getApplicationUrl();

    @BeforeMethod
    public void setUpDemo() {
        String browser = ConfigReader.getBrowser();
        driver = DriverFactory.initializeDriver(browser);
        driver.manage().window().maximize();
        logger.info("Healenium Demo test setup completed");
    }

    @AfterMethod
    public void tearDownDemo() {
        if (DriverFactory.isDriverInitialized()) {
            DriverFactory.quitDriver();
            logger.info("Healenium Demo test teardown completed");
        }
    }

    @Test(groups = {"ui", "healenium-demo"}, description = "Baseline run: store username field locator in Healenium history")
    public void baselineStoreUsernameLocator() {
        driver.navigate().to(LOGIN_URL);

        // Use a stable, correct locator first so Healenium stores the "etalon" selector
        By correctUsername = By.cssSelector("input[data-test='username']");
        WebElement username = driver.findElement(correctUsername);
        username.clear();
        username.sendKeys("baseline_user");

        // Simple assertion to ensure interaction succeeded
        Assert.assertEquals(username.getAttribute("value"), "baseline_user");
        logger.info("Baseline established for username field using valid locator");
    }

    @Test(dependsOnMethods = "baselineStoreUsernameLocator", groups = {"ui", "healenium-demo"},
          description = "Broken locator should be healed by Healenium and still find username field")
    public void healUsernameLocatorAfterChange() {
        driver.navigate().to(LOGIN_URL);

        // Intentionally incorrect/changed locator (typo in attribute value)
        By brokenUsername = By.cssSelector("input[data-test='usernam']");

        // Healenium wraps WebDriver and will attempt to heal this lookup based on stored baseline
        WebElement healedUsername = driver.findElement(brokenUsername);
        healedUsername.clear();
        healedUsername.sendKeys("healed_user");

        Assert.assertEquals(healedUsername.getAttribute("value"), "healed_user",
                "Healenium should heal the broken locator and interact with the username field");
        logger.info("Healenium healed the broken username locator successfully");
    }
}
