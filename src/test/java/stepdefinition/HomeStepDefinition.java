package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.DataStructurePage;
import pages.LaunchPage;
public class HomeStepDefinition {

	WebDriver driver = DriverFactory.getDriver();
	HomePage homePage = new HomePage(driver);
	DataStructurePage dataStructures = new DataStructurePage(driver);
	LaunchPage launchPage = new LaunchPage(driver);

	@Given("The user is on the Home page")
	public void the_user_is_on_the_home_page() {
		System.out.println("Current URL: " + driver.getCurrentUrl());
	}
	
	@When("The user selects {string} from the drop down without Sign in.")
	public void the_user_selects_from_the_drop_down_without_sign_in(String Option) {
		homePage.selectOption(Option);
	}
	
	@Then("The user should able to see an warning message {string}")
	public void the_user_should_able_to_see_an_warning_message(String string) {
		Assert.assertEquals(string, homePage.getErrMsg());
		System.out.println(homePage.getErrMsg());
	}

	@Given("The user is on the Launch page")
	public void the_user_is_on_the_launch_page() {
		System.out.println("Current URL: " + driver.getCurrentUrl());
		}

	@When("The user clicks the Get Started button")
	public void the_user_clicks_the_get_started_button() {
		System.out.println("Clicked on Get Started button");
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

	//	homePage.getDataStructureOptionsText();
		Assert.assertTrue(homePage.getDataStructureOptionsText().contains("Arrays"));
	}
	
	@When("The user clicks Get Started buttons of {string} on the homepage without Sign in")
	public void the_user_clicks_get_started_buttons_of_on_the_homepage_without_sign_in(String TabName) {
		homePage.clickTitlePage(TabName);

	}
	
	@Given("The user is on the Home page after sign in")
	public void the_user_is_on_the_home_page_after_sign_in() {
	//	homePage.clickGetStarted();
		homePage.enterSignIn();
		//pom.

	}

	@When("The user selects {string} from the drop down after Sign in.")
	public void the_user_selects_from_the_drop_down_after_sign_in(String option) {
		homePage.selectOption(option);

	}

	@Then("The user should be able to see {string} page with details.")
	public void the_user_should_be_able_to_see_page_with_details(String OptionName) {
		Assert.assertEquals(OptionName, homePage.getPageHeading(OptionName));//***Asked by Sumathi
	//	Assert.assertEquals(OptionName, homePage.getHeadingTitle()); 
		//System.out.println(dataStructures.getkHeading(OptionName));
	}

	@When("The user clicks Get Started buttons of {string} tab on the homepage after Sign in")
	public void the_user_clicks_Get_Started_buttons_of_tab_on_the_homepage_after_sign_in(String string2) {
		homePage.clickTitlePage(string2);

	}

	@Given("The user has signed in with valid credentials")
	public void the_user_has_signed_in_with_valid_credentials() {
		//homePage.clickGetStarted();
		homePage.enterSignIn();

	}

	@When("The user lands on the Home page after sign in")
	public void the_user_lands_on_the_home_page_after_sign_in() {
		Assert.assertEquals(driver.getCurrentUrl().contains("home"), true);
	}

	@Then("The user should be able to see {string} on the right corner of the Home page")
	public void the_user_should_be_able_to_see_on_the_right_corner_of_the_home_page(String Links) {
		if (Links.equalsIgnoreCase("Sign out")) {
			Assert.assertEquals(Links, homePage.getSignOutLink());
		} else if (Links.equalsIgnoreCase("Mamta.chavan0785@gmail.com")) {
			Assert.assertEquals(Links, homePage.getLoggedInUser());
		}
	}
}
