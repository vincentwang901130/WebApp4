Feature: set attributes of the term

Scenario: Set start,end,enrollstart,enrollend date
  Given initialize a system, login as administrator
  And goto "maintainTerms" page
  When create the term with name="Term B", startDate="2014-09-06", endDate="2014-12-08"
  When set "Term B" with enrollStart "2014-08-01" and enrollEnd "2014-09-30"
  Then show create "Term" "TermB" success