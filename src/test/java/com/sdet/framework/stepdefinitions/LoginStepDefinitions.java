package com.sdet.framework.stepdefinitions;

import com.sdet.framework.drivers.DriverFactory;
import com.sdet.framework.pages.LoginPage;
import com.sdet.framework.utils.ConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoginStepDefinitions - Step definitions for login feature
 */
public class LoginStepDefinitions {

    private static final Logger logger = LogManager.getLogger(LoginStepDefinitions.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        driver = DriverFactory.initializeDriver(browser);
        driver.manage().window().maximize();
        logger.info("Browser initialized for step definitions");
    }

    @After
    public void tearDown() {
        if (DriverFactory.isDriverInitialized()) {
            DriverFactory.quitDriver();
            logger.info("Browser closed");
        }
    }

    @Given("user navigates to login page")
    public void userNavigatesToLoginPage() {
        String appUrl = ConfigReader.getApplicationUrl();
        driver.navigate().to(appUrl);
        loginPage = new LoginPage(driver);
        logger.info("Navigated to login page: " + appUrl);
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");
    }

    @When("user enters email {string}")
    public void userEntersEmail(String email) {
        loginPage.enterEmail(email);
        logger.info("Email entered: " + email);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
        logger.info("Password entered");
    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
        logger.info("Login button clicked");
    }

    @Then("user should see dashboard page")
    public void userShouldSeeDashboardPage() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL: " + currentUrl);
        // Add assertion based on your application
        Assert.assertFalse(currentUrl.contains("login"), "User should not be on login page");
    }

    @Then("user should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        logger.info("Error message: " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage, "Error message should match");
    }

    @Then("user should see {string} message")
    public void userShouldSeeMessage(String result) {
        if (result.equalsIgnoreCase("Dashboard")) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("login"), "User should see dashboard");
            logger.info("User successfully logged in");
        } else if (result.toLowerCase().contains("locked out")) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.toLowerCase().contains("locked"), "Should see locked out message");
            logger.info("Locked out message displayed");
        }
    }
}
