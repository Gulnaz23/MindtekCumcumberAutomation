Feature: Validating etsy titles

  Scenario Outline: Validating etsy separate page titles
    Given user navigate to the Etsy application
    When user clicks on "<Section>" section
    Then user validates title is "<Title>"

    Examples:
      | Title                                 | Section                        |
      | The Best Mother's Day 2022 Gift Ideas | Etsy                           |
      | Jewelry & Accessories \| Etsy         | Jewelry Accessories            |
      | Clothing & Shoes \| Etsy              | Clothing and Shoes             |
      | Home & Living \| Etsy                 | Home and Living                |
      | Wedding & Party \| Etsy               | Wedding and Party              |
      | Toys & Entertainment \| Etsy          | Toys and Entertainment         |
      | Art & Collectibles \| Etsy            | Art and Collectibles           |
      | Craft Supplies & Tools \| Etsy        | Craft Supplies & Tools \| Etsy |
      | The Etsy Gift Guide for 2022 \|Etsy   | Gifts and Gift Cards           |
