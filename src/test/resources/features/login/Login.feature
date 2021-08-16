
Feature:  Login
    In order to get full access to the services 
    As a mobile user James
    James want to be able to login using his mobile number

Background:
    Given James is at the app
@app
Scenario Outline: Login using a valid mobile number
    When he provides his mobile number "<mobile>"
    Then he is able to have full access to the app services

    Examples:
      | mobile     |
      | 9000000    |
