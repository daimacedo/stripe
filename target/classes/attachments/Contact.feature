@SendContactMail
Feature: Send contact sale
  I want to be in contact with the sale's person in stripe

  Scenario: Send an message contact successfully
    Given I am on the home page
    When I acess contact sale page
    And Fill the information's fields
    And I should see a sucessfull contact message

  Scenario Outline: Send an message contact with empty field
    Given I am on the home page
    When I acess contact sale page
    And Fill the information's fields
    But I dont fill the <field>
    Then The <field> should be marked as required

    Examples: 
      | field     |
      | firstName |
      | lastName  |
      | email     |
      | site      |
      | more      |
