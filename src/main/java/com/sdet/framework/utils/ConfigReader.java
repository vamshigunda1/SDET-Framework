package com.sdet.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConfigReader class to read properties from config files
 */
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            fis.close();
            logger.info("Configuration loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration: " + e.getMessage());
            throw new RuntimeException("Config file not found at src/main/resources/config.properties");
        }
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found: " + key);
        }
        return value;
    }

    /**
     * Get property value with default fallback
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get browser name
     * Supports command line override via -Dbrowser=safari
     */
    public static String getBrowser() {
        // First check for system property (command line -Dbrowser=safari)
        String browserFromCmdLine = System.getProperty("browser");
        if (browserFromCmdLine != null && !browserFromCmdLine.trim().isEmpty()) {
            logger.info("Using browser from command line: " + browserFromCmdLine);
            return browserFromCmdLine.trim();
        }
        // Fall back to config.properties
        return getProperty("browser", "chrome");
    }

    /**
     * Get application URL
     */
    public static String getApplicationUrl() {
        return getProperty("app.url");
    }

    /**
     * Get API base URL
     */
    public static String getApiBaseUrl() {
        return getProperty("api.base.url");
    }

    /**
     * Get implicit wait time
     */
    public static long getImplicitWait() {
        return Long.parseLong(getProperty("implicit.wait", "10"));
    }

    /**
     * Get explicit wait time
     */
    public static long getExplicitWait() {
        return Long.parseLong(getProperty("explicit.wait", "15"));
    }

    /**
     * Check if headless mode is enabled
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless.mode", "false"));
    }
}
