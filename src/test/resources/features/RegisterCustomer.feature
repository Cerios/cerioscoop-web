Feature: Register the new customer

  Scenario: Register the new customer using a form
    Given I am on the cerioscoop site
    When I navigate to register
    And I fill in the registerform with valid data and submit it
    Then I check that the customer has been registered
