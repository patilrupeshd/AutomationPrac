Feature: Login test

Background:
Given user launch "Chrome" with automation practice application

@login+ve
Scenario: Verify login with Valid
And user provide valid "username" and "password"
Then user should be on users dashboard

@login-ve
Scenario: Verify login with invalid details
And user provide valid "username" and "invalid"
Then user authentication should failed
