package com.sdet.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestRunner - Cucumber TestNG runner for all features
 */
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.sdet.framework.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/index.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        dryRun = false,
        tags = "@smoke or @regression"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
