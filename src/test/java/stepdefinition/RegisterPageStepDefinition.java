package stepdefinition;


import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;


public class RegisterPageStepDefinition {
	private final PageObjectManager pom;
	WebDriver driver;

	public RegisterPageStepDefinition() {
		pom = new PageObjectManager();
	}

	@Given("User lands on Register page")
	public void user_lands_on_register_page() {
		pom.getregisterpage().validate_Registerlink();
	}

	@When("User clicks Register button after entering valid credentials")
	public void user_clicks_register_button_after_entering_valid_credentials() {
		pom.getregisterpage().Register_with_valid_credentials();
	}

	@Then("User must be navigated to Home page")
	public void user_must_be_navigated_to_home_page() {
		pom.getregisterpage().verifyHomePageNavigation();
	}
	/*
	 * @Then("User must see the registered username on top navigation bar in home page"
	 * ) public void
	 * user_must_see_the_registered_username_on_top_navigation_bar_in_home_page() {
	 * // Write code here that turns the phrase above into concrete actions throw
	 * new io.cucumber.java.PendingException(); }
	 * 
	 * @Then("Sign out link must be present next to username in the top navigation bar in home page"
	 * ) public void
	 * sign_out_link_must_be_present_next_to_username_in_the_top_navigation_bar_in_home_page
	 * () { // Write code here that turns the phrase above into concrete actions
	 * throw new io.cucumber.java.PendingException(); }
	 * 
	 * 
	 * @Then("Success message stating {string} must be displayed below the top navigation bar in home page"
	 * ) public void
	 * success_message_stating_must_be_displayed_below_the_top_navigation_bar_in_home_page
	 * (String string) {
	 * 
	 * }
	 */

	@When("User clicks Register button without filling up username field")
	public void user_clicks_register_button_without_filling_up_username_field() {
		pom.getregisterpage().Register_without_username();
	}

	@Then("user must see {string} tooltip below username field")
	public void user_must_see_tooltip_below_username_field(String expectedString) {

		String actualMessage = pom.getregisterpage().username_validationmessage();
		System.out.println("Validation message for username field: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedString, "Tooltip message mismatch!");

	}

	@When("User clicks Register button with username as special character")
	public void user_clicks_register_button_with_username_as_special_character() {
		pom.getregisterpage().Register_with_specialcharusername();
	}

	@Then("user must see {string} message below the registraion section")
	public void user_must_see_message_below_the_registraion_section(String expectedmessage) {

		String actualmessage = pom.getregisterpage().Error_messagetext();
		System.out.println("Alert message displayed: " + actualmessage);
		Assert.assertEquals(actualmessage, expectedmessage, "Alert message mismatch!");

	}

	@When("User clicks Register button without filling up password field")
	public void user_clicks_register_button_without_filling_up_password_field() {
		pom.getregisterpage().Register_without_password();
	}

	@Then("user must see {string} tooltip below password field")
	public void user_must_see_tooltip_below_password_field(String expectedmessage) {
		String actualmessage = pom.getregisterpage().password_validationmessage();
		System.out.println("Validation message for password field: " + actualmessage);
		Assert.assertEquals(actualmessage, expectedmessage, "Tooltip message mismatch!");
	}

	@When("User clicks Register button without filling up password confirmation field")
	public void user_clicks_register_button_without_filling_up_password_confirmation_field() {
		pom.getregisterpage().Register_without_password();
	}

	@Then("user must see {string} tooltip below password confirmation field")
	public void user_must_see_tooltip_below_password_confirmation_field(String expectedmessage) {
		String actualmessage = pom.getregisterpage().getconfirmpassword_validationmessage();
		System.out.println("Validation message for confirmpassword field: " + actualmessage);
		Assert.assertEquals(actualmessage, expectedmessage, "Tooltip message mismatch!");

	}

	@When("User clicks Register button with entering  numeric password")
	public void user_clicks_register_button_with_entering_numeric_password() {
		pom.getregisterpage().Register_with_numericpassword();
	}

	@When("User clicks Register button with entering special character password")
	public void user_clicks_register_button_with_entering_special_character_password() {
		pom.getregisterpage().Register_with_specialcharpassword();
	}

	@When("User clicks Register button with password less than {int} characters")
	public void user_clicks_register_button_with_password_less_than_characters(int int1) {
		pom.getregisterpage().Register_with_shortpassword();
	}

	@When("User clicks Register button with mismatch password")
	public void user_clicks_register_button_with_mismatch_password() {
		pom.getregisterpage().Register_with_mismatchpassword();
	}

	/*
	 * @When("User clicks logout button present in the Register page") public void
	 * user_clicks_button_present_in_the_register_page(String string) {
	 * pom.getregisterpage().click_logout(); }
	 */
	/*
	 * @Then("User must be navigated to login screen successfully.") public void
	 * user_must_be_navigated_to_login_screen_successfully() {
	 * 
	 * }
	 */

}
