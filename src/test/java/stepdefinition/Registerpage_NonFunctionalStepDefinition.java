package stepdefinition;

import java.util.List;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;

public class Registerpage_NonFunctionalStepDefinition {

	private final PageObjectManager pom;
	 WebDriver driver;

	public Registerpage_NonFunctionalStepDefinition() {
		pom = new PageObjectManager();
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

	@Then("User must see {int} input fields in Register UI")
	public void User_must_see_input_fields_in_register_ui(Integer expectedlabelcount) {
		int actuallabel_count = pom.getregisterpage().getInputFieldCount();
		System.out.println("total input field in Register page is :" + actuallabel_count);
		Assert.assertEquals(actuallabel_count, expectedlabelcount, "label count mismatch");
	}

	@Then("User must see labels with text in Register UI")
	public void user_must_see_labels_with_text_in_register_ui(DataTable dataTable) {
		List<String> expectedlabels = dataTable.asList();

		List<String> actuallabels = pom.getregisterpage().getRegisterlabel_Names();
		Assert.assertEquals(actuallabels, expectedlabels,
				"Label text mismatch , Expected: " + expectedlabels + " but found: " + actuallabels);
	}

	@Then("User must see {int} button in Register UI")
	public void user_must_see_button_in_register_ui(Integer expectedbuttoncount) {
		int actualbuttoncount = pom.getregisterpage().get_button_count();
		System.out.println("total buttons in Register page is :" + actualbuttoncount);
		Assert.assertEquals(actualbuttoncount, expectedbuttoncount, "Button count in Register page is not 1");
	}

	@Then("User should be able to see button with text {string} in Register UI")
	public void user_should_be_able_to_see_button_with_text_in_register_ui(String expectedbuttonname) {
		List<String> actual_buttonname = pom.getregisterpage().getbuttontext();
		System.out.println("actual button name :" + actual_buttonname);
		Assert.assertEquals(actual_buttonname, expectedbuttonname,
				" button text mismatch , Expected: " + expectedbuttonname + " but found: " + actual_buttonname);

	}

	@Then("User must see links with text in Register UI")
	public void user_must_see_links_with_text_in_register_ui(DataTable dataTable) {
		List<String> expectedlink_names = dataTable.asList();

		List<String> actuallink_names = pom.getregisterpage().Registerpagelinktext();

		Assert.assertEquals(actuallink_names, expectedlink_names,
				"Link text mismatch , Expected: " + expectedlink_names + " but found: " + actuallink_names);
	}

	@Then("User must see {string} company name in top nav bar of  Register UI")
	public void user_must_see_company_name_in_top_nav_bar_of_register_ui(String expectedcompany_name) {
		String actualcompany_nameString = pom.getregisterpage().getcompanyString();
		System.out.println("company name displayed is :" + actualcompany_nameString);
		Assert.assertEquals(actualcompany_nameString, expectedcompany_name, "company name mismatch ");
	}

	@Then("User must see list items for password entry field in Register UI")
	public void user_must_see_list_items_for_password_entry_field_in_register_ui(DataTable dataTable) {
		List<String> expectedpassword_reqList = dataTable.asList();

		List<String> actualpassword_reqList = pom.getregisterpage().getPasswordRequirementsText();

		Assert.assertEquals(actualpassword_reqList, expectedpassword_reqList,
				"Link text mismatch , Expected: " + expectedpassword_reqList + " but found: " + actualpassword_reqList);

	}

}
