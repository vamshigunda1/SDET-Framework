package com.sdet.framework.runners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Programmatic TestNG runner so you can start tests from a Java main method.
 *
 * Supported system properties:
 * -Dbrowser=safari|edge|chrome|firefox  (DriverFactory + ConfigReader already read this)
 * -DsuiteFile=path/to/testng.xml        (default: testng.xml at project root)
 * -Dgroups=smoke,ui                      (include groups)
 * -DexcludedGroups=api                   (exclude groups)
 */
public class ProgrammaticTestNG {

    private static final Logger logger = LogManager.getLogger(ProgrammaticTestNG.class);

    public static void main(String[] args) {
        // Set browser system property if not already set (ensures ConfigReader picks it up)
        String browser = System.getProperty("browser");
        if (browser == null || browser.trim().isEmpty()) {
            browser = "chrome";
            System.setProperty("browser", browser);
            logger.info("Browser not specified, defaulting to: {}", browser);
        } else {
            logger.info("Using browser from system property: {}", browser);
        }
        
        String suiteFile = System.getProperty("suiteFile", "testng.xml");
        String groups = System.getProperty("groups");
        String excludedGroups = System.getProperty("excludedGroups");

        logger.info("Starting Programmatic TestNG");
        logger.info("browser={} suiteFile={} groups={} excludedGroups={}", browser, suiteFile, groups, excludedGroups);

        // Resolve suite file path
        File suite = new File(suiteFile);
        if (!suite.exists()) {
            logger.error("Suite file not found: {} (resolved path: {})", suiteFile, suite.getAbsolutePath());
            System.exit(2);
        }

        TestNG testng = new TestNG();

        // Set suite file
        List<String> suites = new ArrayList<>();
        suites.add(suite.getPath());
        testng.setTestSuites(suites);

        // Include/Exclude groups
        if (groups != null && !groups.isBlank()) {
            testng.setGroups(groups);
        }
        if (excludedGroups != null && !excludedGroups.isBlank()) {
            testng.setExcludedGroups(excludedGroups);
        }

        // Run
        try {
            testng.run();
            if (testng.hasFailure()) {
                logger.error("TestNG finished with failures.");
                System.exit(1);
            } else {
                logger.info("TestNG finished successfully.");
            }
        } catch (Exception e) {
            logger.error("Error running TestNG programmatically", e);
            System.exit(1);
        }
    }
}
