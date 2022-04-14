package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyAppHomePage;
import pages.EtsyAppSearchPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.awt.*;
import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {
    WebDriver driver = Driver.getDriver();
    EtsyAppHomePage etsyAppHomePage = new EtsyAppHomePage();
    EtsyAppSearchPage etsyAppSearchPage = new EtsyAppSearchPage();


    @Given("user navigate to the Etsy application")
    public void user_navigate_to_the_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyURL"));

    }

    @When("user searches for {string}")
    public void user_searches_for(String itemName) {

        etsyAppHomePage.searchBox.sendKeys(itemName + Keys.ENTER);
    }

    @When("user applies price filter over {int}")
    public void user_applies_price_filter_over(Integer price) throws InterruptedException {
        Thread.sleep(3000);
        etsyAppSearchPage.allFiltersButton.click();
        etsyAppSearchPage.priceRadioButton.click();
        etsyAppSearchPage.applyButton.click();

    }

    @Then("user validates the items price is equal or over {double}")
    public void user_validates_the_items_price_is_equal_or_over(Double price) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> prices = etsyAppSearchPage.prices;
        for (WebElement element : prices) {
            //it will loop as many times as the WebElement we have
            System.out.println(element.getText());
            String priceStr = element.getText().replace(",", "");
            double doublePrice = Double.parseDouble(priceStr);
            System.out.println(doublePrice);
            Assert.assertTrue(doublePrice >= price);
        }


    }

    @Then("user validates search result items contain keyword {string} or {string}")
    public void userValidatesSearchResultItemsContainKeyword(String item1, String item2) {
        List<WebElement> itemNames = etsyAppSearchPage.itemNames;

        for (int i = 0; i < itemNames.size(); i++) {
            String newItemNames = itemNames.get(i).getText(); // looping through the List of WebElements and getting Text from each WebElement
            boolean condition = newItemNames.toLowerCase(Locale.ROOT).contains(item1) || newItemNames.toLowerCase(Locale.ROOT).contains(item2);
            if (condition) {
                StringBuilder str = new StringBuilder(); // create an StringBuiler object
                str.append(newItemNames); // each time it will add one more string from newItemNames until the List of WebElements has elements

                System.out.println(str);
                Assert.assertTrue(str.toString().toLowerCase(Locale.ROOT).contains(item1) || str.toString().toLowerCase(Locale.ROOT).contains(item2));
            }
        }
    }


    @Then("user validates search result items contain keyword {string}")
    public void userValidatesSearchResultItemsContainKeyword(String arg0) {
    }


}