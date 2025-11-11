package com.sdet.framework.stepdefinitions;

import com.sdet.framework.api.APIClient;
import com.sdet.framework.utils.ConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * APIStepDefinitions - Step definitions for API testing
 */
public class APIStepDefinitions {

    private static final Logger logger = LogManager.getLogger(APIStepDefinitions.class);
    private APIClient apiClient;
    private Response response;

    @Before
    public void setUp() {
        String apiBaseUrl = ConfigReader.getApiBaseUrl();
        apiClient = new APIClient(apiBaseUrl);
        logger.info("API Client initialized");
    }

    @When("I send GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = apiClient.get(endpoint);
        logger.info("GET request sent to: " + endpoint);
    }

    @When("I send POST request to {string} with body:")
    public void sendPostRequest(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        // Convert datatable to map and send as body
        apiClient.addBody(dataTable.asMap());
        response = apiClient.post(endpoint);
        apiClient.reset();
        logger.info("POST request sent to: " + endpoint);
    }

    @When("I send PUT request to {string} with body:")
    public void sendPutRequest(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        apiClient.addBody(dataTable.asMap());
        response = apiClient.put(endpoint);
        apiClient.reset();
        logger.info("PUT request sent to: " + endpoint);
    }

    @When("I send DELETE request to {string}")
    public void sendDeleteRequest(String endpoint) {
        response = apiClient.delete(endpoint);
        logger.info("DELETE request sent to: " + endpoint);
    }

    @Then("response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        logger.info("Response status code: " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status code mismatch");
    }

    @Then("response should contain valid JSON")
    public void verifyValidJson() {
        try {
            response.jsonPath();
            logger.info("Response contains valid JSON");
        } catch (Exception e) {
            Assert.fail("Response does not contain valid JSON: " + e.getMessage());
        }
    }

    @Then("response should contain user id")
    public void verifyUserIdInResponse() {
        String userId = response.jsonPath().getString("id");
        Assert.assertNotNull(userId, "User ID should be present in response");
        logger.info("User ID found in response: " + userId);
    }

    @Then("response should contain updated name {string}")
    public void verifyUpdatedName(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName, "Name should be updated");
        logger.info("Updated name verified: " + actualName);
    }
}
