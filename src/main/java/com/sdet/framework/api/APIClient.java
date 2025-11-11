package com.sdet.framework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

/**
 * APIClient class for handling REST API requests
 */
public class APIClient {

    private static final Logger logger = LogManager.getLogger(APIClient.class);
    private RequestSpecification requestSpec;

    /**
     * Initialize API client with base URL
     */
    public APIClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        this.requestSpec = RestAssured.given();
        logger.info("API Client initialized with base URL: " + baseUrl);
    }

    /**
     * Add headers to request
     */
    public APIClient addHeaders(Map<String, String> headers) {
        headers.forEach((key, value) -> requestSpec.header(key, value));
        logger.info("Headers added: " + headers);
        return this;
    }

    /**
     * Add single header
     */
    public APIClient addHeader(String key, String value) {
        requestSpec.header(key, value);
        logger.info("Header added: " + key + " = " + value);
        return this;
    }

    /**
     * Add query parameters
     */
    public APIClient addQueryParams(Map<String, String> params) {
        requestSpec.queryParams(params);
        logger.info("Query parameters added: " + params);
        return this;
    }

    /**
     * Add request body (JSON)
     */
    public APIClient addBody(Object body) {
        requestSpec.body(body);
        logger.info("Request body added");
        return this;
    }

    /**
     * Add basic authentication
     */
    public APIClient addBasicAuth(String username, String password) {
        requestSpec.auth().basic(username, password);
        logger.info("Basic authentication added");
        return this;
    }

    /**
     * Add bearer token
     */
    public APIClient addBearerToken(String token) {
        requestSpec.header("Authorization", "Bearer " + token);
        logger.info("Bearer token added");
        return this;
    }

    /**
     * Perform GET request
     */
    public Response get(String endpoint) {
        try {
            Response response = requestSpec.get(endpoint);
            logger.info("GET request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("GET request failed: " + endpoint + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Perform POST request
     */
    public Response post(String endpoint) {
        try {
            Response response = requestSpec.post(endpoint);
            logger.info("POST request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("POST request failed: " + endpoint + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Perform PUT request
     */
    public Response put(String endpoint) {
        try {
            Response response = requestSpec.put(endpoint);
            logger.info("PUT request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("PUT request failed: " + endpoint + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Perform DELETE request
     */
    public Response delete(String endpoint) {
        try {
            Response response = requestSpec.delete(endpoint);
            logger.info("DELETE request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("DELETE request failed: " + endpoint + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Perform PATCH request
     */
    public Response patch(String endpoint) {
        try {
            Response response = requestSpec.patch(endpoint);
            logger.info("PATCH request executed: " + endpoint + " - Status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error("PATCH request failed: " + endpoint + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * Reset request specification
     */
    public void reset() {
        this.requestSpec = RestAssured.given();
        logger.info("Request specification reset");
    }
}
