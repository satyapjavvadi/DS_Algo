package stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;

public class HomeStepDefinition {

	private final PageObjectManager pom;

	public HomeStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@Given("The user is on the Home page")
	public void the_user_is_on_the_home_page() {
		System.out.println(ElementUtil.getURL());
	}

	@When("The user selects {string} from the drop down without Sign in.")
	public void the_user_selects_from_the_drop_down_without_sign_in(String Option) {
		pom.getHomePage().selectOption(Option);
	}

	@Then("The user should able to see an warning message {string}")
	public void the_user_should_able_to_see_an_warning_message(String string) {
		Assert.assertEquals(string, pom.getHomePage().getErrMsg());
		System.out.println(pom.getHomePage().getErrMsg());
	}

	@Given("The user is on the Launch page")
	public void the_user_is_on_the_launch_page() {
		System.out.println("Befor clicking Get Started User is on Launch Page");
	}

	@When("The user clicks the Get Started button")
	public void the_user_clicks_the_get_started_button() {
		System.out.println("Clicked on Get Started button");
	}

	@Then("The user should be able to see company name {string}")
	public void the_user_should_be_able_to_see_company_name(String string) {
		Assert.assertEquals("NumpyNinja", pom.getHomePage().getCompanyName());
		System.out.println(pom.getHomePage().getCompanyName());
	}

	@Then("The user should be able to see RegisterLink")
	public void the_user_should_be_able_to_see_register_link() {
		System.out.println(pom.getHomePage().getRegisterLink());
		Assert.assertEquals("Register", pom.getHomePage().getRegisterLink());
	}

	@Then("The user should be able to see Sign in")
	public void the_user_should_be_able_to_see_sign_in() {
		System.out.println(pom.getHomePage().getLogInLink());
		Assert.assertEquals("Sign in", pom.getHomePage().getLogInLink());
	}

	@When("The user clicks the Data Structures dropdown")
	public void the_user_clicks_the_data_structures_dropdown() {
		pom.getHomePage().clickDataStructureDropdown();
	}

	@Then("The user should able to see {int} options Arrays {string} in dropdown menu")
	public void the_user_should_able_to_see_options_arrays_in_dropdown_menu(Integer int6, String string) {
		Assert.assertTrue(pom.getHomePage().getDataStructureOptionsText().contains("Arrays"));
	}

	@When("The user clicks Get Started buttons of {string} on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_on_the_homepage_without_sign_in(String TabName) {
		pom.getHomePage().clickTitlePage(TabName);

	}

	@When("The user selects {string} from the drop down after Sign in.")
	public void the_user_selects_from_the_drop_down_after_sign_in(String option) {
		pom.getHomePage().selectOption(option);

	}

	@Then("The user should be able to see {string} page with details.")
	public void the_user_should_be_able_to_see_page_with_details(String OptionName) {
		Assert.assertEquals(OptionName, pom.getHomePage().getPageHeading(OptionName));
	}

	@When("The user clicks Get Started buttons of {string} tab on the homepage after Sign in")
	public void the_user_clicks_Get_Started_buttons_of_tab_on_the_homepage_after_sign_in(String string2) {
		pom.getHomePage().clickTitlePage(string2);

	}

	@Then("The user should be able to see {string} on the right corner of the Home page")
	public void the_user_should_be_able_to_see_on_the_right_corner_of_the_home_page(String Links) {
		if (Links.equalsIgnoreCase("Sign out")) {
			Assert.assertEquals(Links, pom.getHomePage().getSignOutLink());
		} else if (Links.equalsIgnoreCase("Validuser")) {
			Assert.assertEquals(Links, pom.getHomePage().getLoggedInUser());
		}
	}
}
