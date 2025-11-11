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
 * UserAPITest - Sample API tests for user endpoints
 */
public class UserAPITest {

    private static final Logger logger = LogManager.getLogger(UserAPITest.class);
    private APIClient apiClient;

    @BeforeClass
    public void setUp() {
        String apiBaseUrl = ConfigReader.getApiBaseUrl();
        apiClient = new APIClient(apiBaseUrl);
        apiClient.addHeader("Content-Type", "application/json");
        logger.info("API test setup completed");
    }

    @Test(description = "Get all users - Verify status code 200")
    public void testGetAllUsers() {
        Response response = apiClient.get("/users");
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        logger.info("Get all users test passed");
    }

    @Test(description = "Get user by ID - Verify valid response")
    public void testGetUserById() {
        Response response = apiClient.get("/users/1");
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        String userId = response.jsonPath().getString("id");
        Assert.assertNotNull(userId, "User ID should not be null");
        logger.info("Get user by ID test passed");
    }

    @Test(description = "Create a new user - Verify 201 status code")
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }";
        apiClient.addBody(requestBody);
        
        Response response = apiClient.post("/users");
        
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        apiClient.reset();
        logger.info("Create user test passed");
    }

    @Test(description = "Update user - Verify 200 status code")
    public void testUpdateUser() {
        String requestBody = "{ \"name\": \"Jane Doe\", \"email\": \"jane@example.com\" }";
        apiClient.addBody(requestBody);
        
        Response response = apiClient.put("/users/1");
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        apiClient.reset();
        logger.info("Update user test passed");
    }

    @Test(description = "Delete user - Verify 204 status code")
    public void testDeleteUser() {
        Response response = apiClient.delete("/users/1");
        
        Assert.assertEquals(response.getStatusCode(), 204, "Status code should be 204");
        logger.info("Delete user test passed");
    }
}
