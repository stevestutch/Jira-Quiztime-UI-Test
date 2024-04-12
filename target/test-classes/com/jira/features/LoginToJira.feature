Feature: Login to Jira
    Testing the login process

    Scenario: Login to Jira
        Given I open Jira login
        When I enter valid credentials
        Then I get redirected to QuizTime URL