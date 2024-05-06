Feature: Play game successfully
    Playing the game and answering the questions correctly

    Scenario: Play game successfully
        Given I open Jira login
        When I enter valid credentials
        When I get redirected to QuizTime URL
        When I click on 'Start new Game'
        When I answer 30 questions correctly
        Then The current score should be 30

