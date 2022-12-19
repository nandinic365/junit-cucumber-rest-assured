Feature: All operations related to managing a user

  Scenario: Add New User
    Given I want to add a new user with name as "Gaurav" and Job as "Engineer"
    When i submit user details
    Then user should be added

  Scenario: Fetch a user by providing valid user id
    Given I have a valid userId
    When I fetch user details
    Then user information should be displayed

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  #Scenario: Delete a user by providing valid user 
   # Given I have a valid username
    #When i perform delete operation
    #Then user should be deleted
