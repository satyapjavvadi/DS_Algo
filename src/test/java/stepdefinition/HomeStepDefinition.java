package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomeStepDefinition {
	WebDriver driver = DriverFactory.getDriver();
	HomePage homePage = new HomePage(driver);

	@Given("The user is on the Home page")
	public void the_user_is_on_the_home_page() {

		homePage.clickGetStarted();

	}

	@When("The user selects Stack from the drop down without Sign in.")
	public void the_user_selects_stack_from_the_drop_down_without_sign_in() {
		homePage.clickStackOption();
	}

	@When("The user selects Arrays from the drop down without Sign in.")
	public void the_user_selects_arrays_from_the_drop_down_without_sign_in() {
		homePage.clickArrayOption();
	}

	@When("The user selects Queue from the drop down without Sign in.")
	public void the_user_selects_queue_from_the_drop_down_without_sign_in() {
		homePage.clickQueueOption();
	}

	@When("The user selects Tree from the drop down without Sign in.")
	public void the_user_selects_tree_from_the_drop_down_without_sign_in() {
		homePage.clickTreeOption();
	}

	@When("The user selects Graph from the drop down without Sign in.")
	public void the_user_selects_graph_from_the_drop_down_without_sign_in() {
		homePage.clickGraphOption();
	}

	@Then("The user should able to see an warning message {string}")
	public void the_user_should_able_to_see_an_warning_message(String string) {
		Assert.assertEquals(string, homePage.getErrMsg());
		System.out.println(homePage.getErrMsg());
	}

	@Given("The user is on the Launch page")
	public void the_user_is_on_the_launch_page() {
		homePage.launchUrl("https://dsportalapp.herokuapp.com/");

		// DriverFactory.getDriver().get("https://dsportalapp.herokuapp.com/");
		System.out.println("User is on Launch page");

	}

	@When("The user clicks the Get Started button")
	public void the_user_clicks_the_get_started_button() {
		homePage.clickGetStarted();

	}

	@Then("The user should be able to see company name {string}")
	public void the_user_should_be_able_to_see_company_name(String string) {
		Assert.assertEquals("NumpyNinja", homePage.getCompanyName());
		System.out.println(homePage.getCompanyName());
	}

	@Then("The user should be able to see ResisterLink")
	public void the_user_should_be_able_to_see_resister_link() {
		System.out.println(homePage.getRegisterLink());
		Assert.assertEquals("Register", homePage.getRegisterLink());
	}

	@Then("The user should be able to see Sign in")
	public void the_user_should_be_able_to_see_sign_in() {
		System.out.println(homePage.getLogInLink());
		Assert.assertEquals("Sign in", homePage.getLogInLink());
	}

	@When("The user clicks the Data Structures dropdown")
	public void the_user_clicks_the_data_structures_dropdown() {
		homePage.clickDataStructureDropdown();
	}

	@Then("The user should able to see {int} options Arrays {string} in dropdown menu")
	public void the_user_should_able_to_see_options_arrays_in_dropdown_menu(Integer int6, String string) {

		homePage.getDataStructureOptionsText();
		Assert.assertTrue(homePage.getDataStructureOptionsText().contains("Arrays"));
	}

	//@When("The user clicks Get Started buttons of {string} on the homepage without Sign in")
	//public void the_user_clicks_get_started_buttons_of_on_the_homepage_without_sign_in(String string) {

	// homePage.clickArrayGetStarted();
	// }
	//@When("The user clicks Get Started buttons of <Heading> on the homepage without Sign in")
	//public void the_user_clicks_get_started_buttons_of_heading_on_the_homepage_without_sign_in(String Heading) {
	// homePage.clickHeadings(Heading);
	//}
	@When("The user clicks Get Started buttons of Linked List on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_linked_list_on_the_homepage_without_sign_in() {
		homePage.clickLinkedListGetStarted();
	}

	@When("The user clicks Get Started buttons of Queue on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_queue_on_the_homepage_without_sign_in() {
		homePage.clickQueueGetStarted();

	}

	@When("The user clicks Get Started buttons of Array on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_array_on_the_homepage_without_sign_in() {
		homePage.clickArrayGetStarted();
	}

	@When("The user clicks Get Started buttons of Stack on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_stack_on_the_homepage_without_sign_in() {
		homePage.clickStackGetStarted();
	}

	@When("The user clicks Get Started buttons of Tree on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_tree_on_the_homepage_without_sign_in() {
		homePage.clickTreeGetStarted();
	}

}
