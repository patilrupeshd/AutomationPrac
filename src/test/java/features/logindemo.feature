Feature: Login test

Background:
Given user launch "Firefox" with automation practice application

Scenario: Verify login with Valid
And user provide valid "username" and "Password"
Then user should be on users dashboard
And user close the application 
