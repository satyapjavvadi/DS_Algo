package stepdefinition;

import io.cucumber.java.en.*;
import pages.RegisterPage;

public class RegisterPageStepDefinition {
	
	@Given("User lands on Register page")
	public void user_lands_on_register_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	

	@When("User clicks Register button after entering valid credentials")
	public void user_clicks_register_button_after_entering_valid_credentials() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("User must be navigated to Home page")
    public void user_must_be_navigated_to_home_page() {
        
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
	 */

	@Then("Success message stating {string} must be displayed below the top navigation bar in home page")
	public void success_message_stating_must_be_displayed_below_the_top_navigation_bar_in_home_page(String string) {
	   
	}

	@When("User clicks Register button without filling up username field")
	public void user_clicks_register_button_without_filling_up_username_field() {
	   
	}

	@Then("user must see {string} tooltip below username field")
	public void user_must_see_tooltip_below_username_field(String expectedmessage) {
		/*
		 * String actualMessage = registerPage.getUsernameValidationMessage();
		 * System.out.println("Validation message: " + actualMessage);
		 * Assert.assertEquals(actualMessage, expectedMessage,
		 * "Tooltip message mismatch!");
		 */
	}

	@When("User clicks Register button with username as special character")
	public void user_clicks_register_button_with_username_as_special_character() {
	   
	}

	@Then("user must see {string} message below the registraion section")
	public void user_must_see_message_below_the_registraion_section(String expectedmessage) {
		/*
		 * String actualMessage = registerPage.getAlertMessageText();
		 * System.out.println("Alert message displayed: " + actualMessage);
		 * Assert.assertEquals(actualMessage, expectedMessage,
		 * "Alert message mismatch!");
		 */
	}

	@When("User clicks Register button without filling up password field")
	public void user_clicks_register_button_without_filling_up_password_field() {
	    
	}

	@Then("user must see {string} tooltip below password field")
	public void user_must_see_tooltip_below_password_field(String string) {
	    
	}

	@When("User clicks Register button without filling up password confirmation field")
	public void user_clicks_register_button_without_filling_up_password_confirmation_field() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("user must see {string} tooltip below password confirmation field")
	public void user_must_see_tooltip_below_password_confirmation_field(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks Register button with entering  numeric password")
	public void user_clicks_register_button_with_entering_numeric_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks Register button with entering special character password")
	public void user_clicks_register_button_with_entering_special_character_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks Register button with password less than eight characters")
	public void user_clicks_register_button_with_password_less_than_characters() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks Register button with mismatch password")
	public void user_clicks_register_button_with_mismatch_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	

	@When("User clicks logout button present in the Register page")
	public void user_clicks_button_present_in_the_register_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	/*
	 * @Then("User must be navigated to login screen successfully.") public void
	 * user_must_be_navigated_to_login_screen_successfully() {
	 * 
	 * }
	 */

}
