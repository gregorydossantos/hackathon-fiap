# language: en

  Feature: Query users

    Scenario: Query all users from database
      Given that i have registered users in my database
      When i do an request GET
      Then response must be status code 200
      And the body must contain a list of users