Feature: Start the game from initial view
    Starting the game game from the initial view

    Scenario: Start the game
        Given I open Jira login
        When I enter valid credentials
        When I get redirected to QuizTime URL
        When I click on 'Start new Game'
        Then I should see the game field with all elements