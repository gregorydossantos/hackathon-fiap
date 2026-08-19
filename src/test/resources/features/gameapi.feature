# language: en

Feature: Games

  Scenario: Get all games
    Given i do an request GET in the resource games
    Then games response must be status code 200
    And the body must contain a list of games