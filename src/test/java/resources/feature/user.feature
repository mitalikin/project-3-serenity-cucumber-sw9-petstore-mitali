Feature: Testing different request on the petUser application

  Scenario Outline: Create a new user & verify if the user is added
    When I create a new user by providing the information id "<id>" username "<userName>" firstName "<firstName>" lastName "<lastName>" email "<email>"password"<password>"phone"<phone>"userStatus"<userStatus>"
    Then I verify that the user with "<userName>" is created
    Examples:
      | id  | userName | firstName | lastName | email            | password | phone       | userStatus |
      | 102 | Testing  | software  | selenium | prime2@gmail.com | Tomtpom  | 08766734677 | 0          |