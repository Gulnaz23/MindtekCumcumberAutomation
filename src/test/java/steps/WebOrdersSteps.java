package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrderHomePage;
import pages.WebOrdersLoginPage;
import pages.WebOrdersOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class WebOrdersSteps {

    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrderHomePage webOrdersHomePage = new WebOrderHomePage();
    WebOrdersOrderPage webOrdersOrderPage = new WebOrdersOrderPage();

    @Given("user navigates to the weborders application")
    public void user_navigates_to_the_weborders_application() {

        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user provides username {string} and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrdersLoginPage.usernameBox.sendKeys(username);
        webOrdersLoginPage.passwordBox.sendKeys(password);
        webOrdersLoginPage.loginButton.click();

    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Web Orders";

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.quit();
    }

    @Then("user validates error message {string}")
    public void userValidatesErrorMessage(String errorMessage) {
        String actualMessage = webOrdersLoginPage.errorMessage.getText();
        Assert.assertEquals(errorMessage, actualMessage);

    }

    @When("user navigates to the Order module")
    public void user_navigates_to_the_Order_module() {
        webOrdersHomePage.orderModule.click();
    }

    @When("user selects {string} product with {int}")
    public void user_selects_product_with(String product, Integer quantity) {
        BrowserUtils.selectDropdownByValue(webOrdersOrderPage.orderProductDropdown, product);
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(quantity + "" + Keys.ENTER);

    }

    @Then("user validates total is calculated properly for quantity {int}")
    public void user_validates_total_is_calculated_properly_for_quantity(Integer quantity) {

        String pricePerUnit = webOrdersOrderPage.pricePerUnit.getAttribute("value");
        int pricePerUnitInt = Integer.parseInt(pricePerUnit); //// making String pricePerUnit as Integer pricePerUnitInt to perform math calculation
        int expectedTotal = 0; // createing an empty container for total in order to use it later

        String discountAmount = webOrdersOrderPage.discountBox.getAttribute("value"); // works with String only
        int discountAmountInt = Integer.parseInt(discountAmount); // making String discount as Integer discountInt to perform math calculation


        if (discountAmountInt == 0) {
            expectedTotal = quantity * pricePerUnitInt;
        } else {
            expectedTotal = quantity * pricePerUnitInt; // expectedTotal is 1000
            expectedTotal = expectedTotal - expectedTotal * discountAmountInt / 100; // 1000 - (1000 * 8/100)
        }
        String actualTotalAmount = webOrdersOrderPage.totalBox.getAttribute("value");
        int actualTotal = Integer.parseInt(actualTotalAmount);
        Assert.assertEquals(expectedTotal, actualTotal);

    }

}