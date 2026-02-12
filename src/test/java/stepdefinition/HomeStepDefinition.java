package stepdefinition;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class HomeStepDefinition {

	private final PageObjectManager pom;
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

	public HomeStepDefinition(PageObjectManager pom) {
		this.pom = pom;
	}

	@When("The user selects {string} from the drop down without Sign in.")
	public void the_user_selects_from_the_drop_down_without_sign_in(String Option) {
		pom.getHomePage().selectOption(Option);
	}

	@Then("The user should able to see an warning message {string}")
	public void the_user_should_able_to_see_an_warning_message(String warningMsg) {
		Assert.assertEquals(warningMsg, pom.getHomePage().getErrMsg(), "Warning message mismatch");
		logger.info("Warning message displayed: " + pom.getHomePage().getErrMsg());
	}

	@Then("The user should be able to see company name {string}")
	public void the_user_should_be_able_to_see_company_name(String companyName) {
		Assert.assertEquals(companyName, pom.getHomePage().getCompanyName(), "Company name mismatch");
		logger.info("Company name displayed: " + pom.getHomePage().getCompanyName());
	}

	@Then("The user should be able to see {string}")
	public void the_user_should_be_able_to_see(String linkName) {
		Assert.assertEquals(linkName, pom.getHomePage().getLinkName(linkName), "Link name mismatch");
	}

	@When("The user clicks the Data Structures dropdown")
	public void the_user_clicks_the_data_structures_dropdown() {
		pom.getHomePage().clickDataStructureDropdown();
	}

	@Then("The user should able to see all options")
	public void the_user_should_able_to_see_all_options(DataTable dataTable) {
		List<String> expectedOptions = dataTable.asList();
		logger.info("Expected dropdown options: " + expectedOptions);

		List<String> actualOptions = pom.getHomePage().getDataStructureOptionsText();
		logger.info("Actual dropdown options: " + actualOptions);

		Assert.assertEquals(actualOptions, expectedOptions, "Dropdown options mismatch");
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
		Assert.assertEquals(OptionName, pom.getHomePage().getPageHeading(OptionName), "Page heading mismatch");
	}

	@When("The user clicks Get Started buttons of {string} tab on the homepage after Sign in")
	public void the_user_clicks_Get_Started_buttons_of_tab_on_the_homepage_after_sign_in(String optionTab) {
		pom.getHomePage().clickTitlePage(optionTab);

	}

	@Then("The user should be able to see {string} on the right corner of the Home page")
	public void the_user_should_be_able_to_see_on_the_right_corner_of_the_home_page(String Links) {
		Assert.assertEquals(Links, pom.getHomePage().getRightCornerLink(Links), "Link name mismatch");
	}
}
