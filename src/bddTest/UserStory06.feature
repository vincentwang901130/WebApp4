Feature: the constraints between term date and course date

Scenario: Term Create	
	Given initialize a system, login as admin
	And goto "maintainTerms" page
	When create the term with name="test4", startDate="2015-05-01", endDate="2015-08-01"
	Then show create "Term" "test4" success
	
Scenario: set enrolldate
	Given initialize a system, login as admin
	When set term "Term A" enrolldate to"2015-01-01"
	When click update button
	Then update failed "invalid date"