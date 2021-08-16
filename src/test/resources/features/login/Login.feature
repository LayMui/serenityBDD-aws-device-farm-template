
Feature:  Login
    In order to get full access to the services for non main account holder
    As a mobile non main account user, James
    James want to be able to use otp (One-Time Password) 

Background:
    Given James is at the app
@app
Scenario Outline: Login using a valid mobile number
    When he provides his mobile number "<mobile>"
    Then he is able to have full access to the app services

    Examples:
      | mobile     |
      | 9000000    |
