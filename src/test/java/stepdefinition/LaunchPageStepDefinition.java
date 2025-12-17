package stepdefinition;

import java.util.List;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class LaunchPageStepDefinition {

    private final PageObjectManager pom;

    public LaunchPageStepDefinition(PageObjectManager pom) {
        this.pom = pom;
    }

    @Given("the user has a browser open")
    public void the_user_has_a_browser_open() {
        pom.getLaunchPage().browserIsOpen();
    }

    @When("the user enters the correct DS Algo portal URL")
    public void the_user_enters_the_correct_ds_algo_portal_url() {
        System.out.println("Current URL: " + pom.getLaunchPage().getCurrentUrl());
    }

    @Then("the user should be on the DS Algo Portal page")
    public void the_user_should_be_on_the_ds_algo_portal_page(DataTable dataTable) {
		String expectedTextInURL = dataTable.cell(0,0);
         Assert.assertTrue(pom.getLaunchPage().getCurrentUrl().contains(expectedTextInURL), "User is not on the DS Algo Portal page");
    }

    @Then("the user should be able to see the content text on the Launch page")
    public void theUserShouldBeAbleToSeeTheContentTextOnTheLaunchPage(DataTable dataTable) {
        List<String> expectedTexts = dataTable.asList(String.class);
        for (String expectedText : expectedTexts) {
            Assert.assertTrue(pom.getLaunchPage().doesPageContainText(expectedText),
                    "Content text not visible: " + expectedText);
        }
    }

    @Then("the user should be able to see {int} button on the Launch page")
    public void theUserShouldBeAbleToSeeButtonOnTheLaunchPage(int expectedButtonCount) {
        Assert.assertEquals(pom.getLaunchPage().getButtonCount(), expectedButtonCount, "Button count does not match.");
    }

    @Then("the user should be able to see button with text {string}")
    public void theUserShouldBeAbleToSeeButtonWithText(String expectedButtonText) {
        Assert.assertTrue(pom.getLaunchPage().doesPageContainButtonWithText(expectedButtonText),
                "Button text does not match: " + expectedButtonText);
    }


    // *****************************************************************************//
    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonText) {
        pom.getLaunchPage().clickButtonByText(buttonText);
        Assert.assertTrue(pom.getLaunchPage().isButtonEnabled(buttonText), "Button not clickable: " + buttonText);
    }


}
