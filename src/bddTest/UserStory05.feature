Feature: create course and check repeat creation, maintainCourseForm:j_idt99 or maintainCourseForm:j_idt50

  Scenario: Create Term
    Given initialize a system, login as admin
    And goto "maintainCourses" page
    When select "Term A"
    And create a course which name is "OOSystemDev", courseCode="COMP5104", meeting Times are "Mon and Wed", time="11:35-12:55", location="ME 3174"
    Then show course "COMP5104" create success

  Scenario: Duplicated Term Creation
    Given initialize a system, login as admin
    And goto "maintainCourses" page
    When select "Term A"
    And create a course which name is "OOSystemDev", courseCode="COMP5104", meeting Times are "Mon and Wed", time="11:35-12:55", location="ME 3174"
    Then show course "COMP5104" create failed
