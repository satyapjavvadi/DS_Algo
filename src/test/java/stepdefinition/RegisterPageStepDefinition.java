package stepdefinition;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class RegisterPageStepDefinition {
	private final PageObjectManager pom;
	// private WebDriver driver;

	public RegisterPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@When("User clicks Register link in home page")
	public void user_clicks_register_link_in_home_page() {
		pom.getregisterpage().Registerlink_click();
	}

	@Then("user lands on Register page")
	public void user_lands_on_Register_page() {
		System.out.println("user is on Register page: " + pom.getregisterpage().Current_link_Check());

		Assert.assertTrue(pom.getregisterpage().Current_link_Check().contains("register"),
				"user is not on Register page");

	}

	@When("User clicks Register button after entering valid credentials {string}, {string}, {string}")
	public void user_clicks_register_button_after_entering_valid_credentials(String username, String password,
			String confirmPassword) {
		pom.getregisterpage().Register_User(username, password, confirmPassword);

	}

	@Then("User must be navigated to Home page")
	public void user_must_be_navigated_to_home_page() {

		System.out.println("user is on Home page:" + pom.getregisterpage().Current_link_Check());

		Assert.assertTrue(pom.getregisterpage().Current_link_Check().contains("home"), "user is not on Home page");

	}

	@When("User clicks Register button after entering testdata {string} , {string} ,{string}")
	public void user_clicks_register_button_after_entering_testdata(String username, String password,
			String confirmpassword) {
		pom.getregisterpage().Register_User(username, password, confirmpassword);

	}

	@Then("User must see {string} in Register UI")
	public void user_must_see_in_register_ui(String errormessage) {

		String actualerror_msg = pom.getregisterpage().Registerpage_errormessage();

		Assert.assertEquals(actualerror_msg, errormessage, "error message mismatch");
	}

}
