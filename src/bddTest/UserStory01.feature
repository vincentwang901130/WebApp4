Feature: Register

Scenario: A Student want to register in the system
    
    Given student A open the register page
    When Student A enters his firstname "AAA" last name "AAA" Birthdate"1991-5-24"
    When Student A click on the submit button
    Then The student sees the messagebox contain a student number and password

    
