Feature: API Testing
  As a QA Engineer
  I want to test REST APIs
  So that I can ensure API functionality is working correctly

  Scenario: Get list of users via API
    When I send GET request to "/users"
    Then response status code should be 200
    And response should contain valid JSON

  Scenario: Create a new user via API
    When I send POST request to "/users" with body:
      | name  | John Doe        |
      | email | john@example.com |
    Then response status code should be 201
    And response should contain user id

  Scenario: Update user information
    When I send PUT request to "/users/1" with body:
      | name | Jane Doe |
    Then response status code should be 200
    And response should contain updated name "Jane Doe"

  Scenario: Delete a user
    When I send DELETE request to "/users/1"
    Then response status code should be 204
