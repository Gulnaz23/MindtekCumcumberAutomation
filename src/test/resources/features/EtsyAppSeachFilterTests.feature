@regression  @OMT-7925
Feature: Validating Etsy Application search and filter functionalities

    Background: Repeated first steps in ech scenario
    Given user navigate to the Etsy application
    When user searches for "carpet"
    #@Before method will run

  Scenario: Validating price range filter functionality for searched items
    And user applies price filter over 1000
    And user validates the items price is equal or over 1000.00
    #@After method will run

      #@Before method will run
  @OMT-7926
 Scenario: Validating search results
   Then user validates search result items contain keyword "carpet"
      #@After method will run
