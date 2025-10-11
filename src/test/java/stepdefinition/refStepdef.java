package stepdefinition;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import org.testng.Assert;
import pages.PageObjectManager;

import java.util.List;
import java.util.Set;

public class refStepdef {


    private final PageObjectManager pom;



    public refStepdef() {

        pom = new PageObjectManager();

    }


    @Then("the user should be on the DS Algo Portal page")
    public void theUserShouldBeOnTheDSAlgoPortalPage() {

        Assert.assertTrue(pom.getLaunchpage().getCurrentUrl().contains("dsportalapp"), "User is not on the DS Algo Portal page");
    }

    @Then("the user should be able to see the content text on the Launch page")
    public void theUserShouldBeAbleToSeeTheContentTextOnTheLaunchPage(DataTable dataTable) {
        List<String> expectedTexts = dataTable.asList(String.class);
        String actualText = pom.getLaunchpage().getAllFieldSpellings();
        System.out.println("actualText = " + actualText);

        for(String expectedText : expectedTexts) {
            System.out.println("expectedText = " + expectedText);
            Assert.assertTrue(actualText.contains(expectedText), "content text is not visible");
            }
        }

    @Then("the user should be able to see {int} button on the Launch page")
    public void theUserShouldBeAbleToSeeButtonOnTheLaunchPage(int expectedButtonCount) {
        int actualButtonCount = pom.getLaunchpage().getButtonText().size();
        System.out.println("actualButtonCount = " + actualButtonCount);
        Assert.assertEquals(actualButtonCount, expectedButtonCount, "Button count does not match.");
    }

    @Then("the user should be able to see button with text {string}")
    public void theUserShouldBeAbleToSeeButtonWithText(String expectedButtonText) {
        List<String> actualButtonText = pom.getLaunchpage().getButtonText();
        System.out.println("actualButtonText = " + actualButtonText);
        Assert.assertTrue(actualButtonText.contains(expectedButtonText), "Button text does not match.");
    }


    @Then("The user should be redirected to the home page")
    public void theUserShouldBeRedirectedToTheHomePage() {
        Assert.assertTrue(pom.getLaunchpage().getCurrentUrl().contains("home"), "User is not redirected to the home page");
    }

    @When("the user clicks on {string} button")
    public void theUserClicksOnButton(String buttonText) {
        pom.getLaunchpage().clickButtonByText(buttonText);
    }

    @Then("the user should see linkTexts")
    public void theUserShouldSeeLink(DataTable dataTable) {
        List<String> expectedLinkTexts = dataTable.asList(String.class);
        Set<String> actualLinkTexts = pom.getLaunchpage().getAllDropDownLinks();
        System.out.println("actualLinkTexts = " + actualLinkTexts);
        Assert.assertTrue(actualLinkTexts.containsAll(expectedLinkTexts), "Not all expected links are present in the dropdown.");


    }
}
