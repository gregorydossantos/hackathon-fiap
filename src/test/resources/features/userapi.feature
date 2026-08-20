# language: en

Feature: Users

  @test
  Scenario: Get all users
    Given i do an request GET in the resource users
    Then response from GET must be status code 200
    And the body must contain a list of users

  @test
  Scenario: Create a user
    Given i send a POST request with name email password exchange successfully
    Then response from POST must be status code 201

  @test
  Scenario: Update user
    Given i send a PATCH request with fields that i wanna change successfully 5
    Then response from PATCH must be status code 200
    And the body must contain updated user

  @test
  Scenario: Delete user
    Given i do an request DELETE passing the id user 12
    And user was deleted from database 12
    Then response from DELETE must be status code 200