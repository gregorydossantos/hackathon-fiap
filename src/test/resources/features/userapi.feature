# language: en

  Feature: Users

    Scenario: Query all users from database
      Given i send a POST request with name email password exchange successfully
      And response must be http status code 201
      When i do an request GET
      Then response must be status code 200
      And the body must contain a list of users