@uitest
Feature: Login Functionality
  As a user
  I want to login with valid credentials
  So that I can access the application

  Background:
    Given user navigates to login page

  Scenario: Successful login with valid credentials
    When user enters email "standard_user@saucedemo.com"
    And user enters password "secret_sauce"
    And user clicks login button
    Then user should see dashboard page

  Scenario: Failed login with invalid password
    When user enters email "standard_user@saucedemo.com"
    And user enters password "invalid_password"
    And user clicks login button
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"

  Scenario Outline: Login with different users
    When user enters email "<email>"
    And user enters password "<password>"
    And user clicks login button
    Then user should see "<result>" message

    Examples:
      | email                         | password      | result            |
      | standard_user@saucedemo.com   | secret_sauce  | Dashboard         |
      | locked_out_user@saucedemo.com | secret_sauce  | locked out        |
      | problem_user@saucedemo.com    | secret_sauce  | Dashboard         |
