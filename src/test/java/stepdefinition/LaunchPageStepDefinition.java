package stepdefinition;

import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;
import io.cucumber.java.en.*;

public class LaunchPageStepDefinition {
	
	WebDriver driver;
	
	@Given("the user has a browser open")
	public void the_user_has_a_browser_open() {
		
		driver = DriverFactory.getDriver();


	}

	@When("the user enters the correct DS Algo portal URL")
	public void the_user_enters_the_correct_ds_algo_portal_url() {


	}

	@Then("the user should be able to see the DS Algo portal with Preparing for the Interviews, Get Started button and Copyright info")
	public void the_user_should_be_able_to_see_the_ds_algo_portal_with_preparing_for_the_interviews_get_started_button_and_copyright_info() {


	}

	@Given("the user is on the DS Algo Portal page")
	public void the_user_is_on_the_ds_algo_portal_page() {


	}

	@When("the launch page loads")
	public void the_launch_page_loads() {


	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String string) {


	}

	@Then("the user should be able to see {string} button")
	public void the_user_should_be_able_to_see_button(String string) {


	}

	@When("the user clikcs the {string} button")
	public void the_user_clikcs_the_button(String string) {


	}

	@Then("the user shold be navigated to home page with NumpyNinja, Data Structures, Register and Sign in links")
	public void the_user_shold_be_navigated_to_home_page_with_numpy_ninja_data_structures_register_and_sign_in_links() {


	}




}
