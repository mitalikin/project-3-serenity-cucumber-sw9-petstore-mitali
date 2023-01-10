Feature: User information
As a  user I want to Test User information


  Scenario Outline: CRUD TEst
    Given User information is running
    When I create a new user by providing the information id "<id>" username "<userName>" firstName "<firstName>" lastName "<lastName>" email "<email>"password"<password>"phone"<phone>"userStatus"<userStatus>"
    Then I verify that the user with "<userName>" is created
    And I update the user with information id "<id>" username "<userName>" firstName "<firstName>" lastName "<lastName>" email "<email>"password"<password>"phone"<phone>"userStatus"<userStatus>"
    And The user with username "<userName>" is updated successfully
    And I delete the user that created with username"<userName>"
    Then The user deleted successfully
    Examples:
      | id  | userName | firstName | lastName | email            | password | phone       | userStatus |
      | 102 | Testing  | software  | selenium | prime2@gmail.com | Tomtpom  | 08766734677 | 0          |