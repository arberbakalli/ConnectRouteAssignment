@debug
Feature:Routee

  Scenario: Create a valid contact
    Given I am authenticated
    When I create a valid contract
    Then I should see a valid contact confirmation

  Scenario: Send an Sms analyse campaign
    Given I am authenticated
    When I send a valid sms
    Then I should see a valid sms confirmation