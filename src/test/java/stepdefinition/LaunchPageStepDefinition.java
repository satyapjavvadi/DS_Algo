package stepdefinition;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;
import io.cucumber.java.en.*;

import java.util.List;

public class LaunchPageStepDefinition {
	
	WebDriver driver;



	@When("the user clicks the Get Started button")
	public void the_user_clicks_the_button( ) {



	}
   	@Then("The user should be redirected to the home page")
	public void theUserShouldBeRedirectedToTheHomePage() {
	}

	@When("the user clicks the {string} button")
	public void theUserClicksTheButton(String arg0) {
	}

	@Then("the user should be on the DS Algo Portal page")
	public void theUserShouldBeOnTheDSAlgoPortalPage() {
	}

	@Then("the user should be able to see the content text on the Launch page")
	public void theUserShouldBeAbleToSeeTheContentTextOnTheLaunchPage(DataTable dataTable) {
		List<List<String>> data = dataTable.asLists(String.class);
	}

	@Then("the user should be able to see {int} button on the Launch page")
	public void theUserShouldBeAbleToSeeButtonOnTheLaunchPage(int arg0) {
	}

	@Then("the user should be able to see button with text {string}")
	public void theUserShouldBeAbleToSeeButtonWithText(String expectedButtonText) {
	}
}
