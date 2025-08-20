@login
Feature: Login to OpenCart

  Background:
    Given the user is on the OpenCart homepage

  @positive
  Scenario: Login with valid credentials (from config)
    When the user navigates to the Login page
    And the user logs in with valid credentials from config
    Then the user should see they are logged in

  @negative
  Scenario Outline: Login with invalid credentials
    When the user navigates to the Login page
    And the user logs in with email "<email>" and password "<password>"
    Then the user should see a login error

    Examples:
      | email               | password  |
      | wrong@example.com   | wrongpass |
      | invalid@example.com | 123456    |
