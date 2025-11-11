package com.sdet.framework.tests.api;

import com.sdet.framework.api.APIClient;
import com.sdet.framework.utils.ConfigReader;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ProductAPITest - Sample API tests for product endpoints
 */
public class ProductAPITest {

    private static final Logger logger = LogManager.getLogger(ProductAPITest.class);
    private APIClient apiClient;

    @BeforeClass
    public void setUp() {
        String apiBaseUrl = ConfigReader.getApiBaseUrl();
        apiClient = new APIClient(apiBaseUrl);
        apiClient.addHeader("Content-Type", "application/json");
        logger.info("Product API test setup completed");
    }

    @Test(description = "Get all products - Verify status code 200")
    public void testGetAllProducts() {
        Response response = apiClient.get("/products");
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        logger.info("Get all products test passed");
    }

    @Test(description = "Get product by ID - Verify valid response")
    public void testGetProductById() {
        Response response = apiClient.get("/products/1");
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        String productId = response.jsonPath().getString("id");
        Assert.assertNotNull(productId, "Product ID should not be null");
        logger.info("Get product by ID test passed");
    }

    @Test(description = "Get products with pagination")
    public void testGetProductsWithPagination() {
        apiClient.addQueryParams(java.util.Map.of("page", "1", "limit", "10"));
        Response response = apiClient.get("/products");
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        apiClient.reset();
        logger.info("Get products with pagination test passed");
    }
}
