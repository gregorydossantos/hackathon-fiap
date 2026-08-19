# language: en

  Feature: Users

    Scenario: Get all users
      Given i do an request GET in the resource users
      Then users response must be status code 200
      And the body must contain a list of users

    Scenario: Create a user
      Given i send a POST request with name email password exchange successfully
      Then return must be status code 201

    Scenario: Update user
      Given i send a PATCH request with fields that i wanna change
      Then users response must be status code 200
      And the body must contain a list of users

    Scenario: Delete user
      Given i do an request GET in the resource users
      Then users response must be status code 200
      And the body must contain a list of users