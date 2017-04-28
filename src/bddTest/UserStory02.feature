Feature: login

  Scenario: Student login the system
    Given student A on open the system website
    When Student A enters student number "stu1" and password "12345"
    When Student A click on the button
    Then There student A will be on the "Welcome dog" page

  Scenario: random login failure
  	Given login in with correct student number "stu1" and "xxx"
  	When click the register button
  	Then fail to login the system