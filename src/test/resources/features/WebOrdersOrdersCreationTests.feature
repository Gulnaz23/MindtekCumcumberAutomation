@OMT-7929
Feature: Validating calculate and order creation functionalities
    Scenario Outline: Validation calculate functionality
      Given user navigates to the weborders application
      When user provides username "Tester" and password "test"
      And user navigates to the Order module
      And user selects "<product>" product with <quantity>
      Then user validates total is calculated properly for quantity <quantity>

      Examples:
        | product     | quantity |
        | MyMoney     | 1        |
        | FamilyAlbum | 100      |
        | P | 55       |
        | MyMoney     | 20       |
