Feature: TermCreate

  Scenario: Admmin create term
    Given System setup and Admin loggin the system
    And Admin click the "maintainTerm" button
    When Admin enters termname "FALL" startdate "2017-09-05" enddate "2017-12-9"
    When Admin click create button
    Then The message shows creating Term "FALL" successfully

  Scenario: Testing Overlapping
    Given Admin login and create a term with termname "FALL1", startDate="2012-09-10", endDate="2012-12-20"
    And The message shows create term "FALL1" successful
    When Admin enters termname "FALL2" startdate "2012-06-01" enddate "2012-10-12"
    Then The message shows create Term "FALL2" failed
