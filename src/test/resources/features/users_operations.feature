Feature: All operations related to managing a user

  Scenario: Add New User
    Given I want to add a new user with name as "Gaurav" and Job as "Engineer"
    When i submit user details
    Then user should be added

  Scenario: Fetch a user by providing valid user id
    Given I have a valid userId
    When I fetch user details
    Then user information should be displayed
  
 Scenario: Update an existing user
    Given I have a valid user
    When I update the user name as "Nandini" and Job as "Software Engineer"
    Then the user details should be updated

  Scenario: delete an existing user by providing valid user id
    Given I have a valid userId as "2"
    When  I invoke the delete operation
    Then the user details should be deleted



