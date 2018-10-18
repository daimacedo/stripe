@SendCV
Feature: Apply for a job position
  I want to be part of the stripe's team

  Scenario: Send a CV successfully
    Given I am on the job's page
    When I Fill the information's fields
    Then I should see a sucessfull job apply message

  Scenario Outline: Send a CV with empty field
    Given I am on the job's page
    When I Fill the information's fields
    But I dont fill the <field>
    Then The <field> should be marked as required

    Examples: 
      | field         |
      | firstName-Job |
      | lastName-Job  |
      | email-Job     |
      | phone-Job     |
