package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;

public class Registerpage_NonFunctionalStepDefinition {

	private final PageObjectManager pom;

	private final String filePath;
	private final String sheetName;

	public Registerpage_NonFunctionalStepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");
		sheetName = ConfigReader.getProperty("Registerlabels");
	}

	@When("User clicks Register link in home UI")
	public void user_clicks_register_link_in_home_ui() {
		pom.getregisterpage().Registerlink_click();
	}

	@Then("user lands on Register UI")
	public void user_lands_on_Register_ui() {
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

	@Then("User must see links with text in Register UI")
	public void user_must_see_links_with_text_in_register_ui(DataTable dataTable) {
		List<String> expectedlink_names = dataTable.asList();

		List<String> actuallink_names = pom.getregisterpage().Registerpagelinktext();

		Assert.assertEquals(actuallink_names, expectedlink_names,
				"Link text mismatch , Expected: " + expectedlink_names + " but found: " + actuallink_names);
	}

	@Then("User must see {int} button in Register UI")
	public void user_must_see_button_in_register_ui(Integer expectedbuttoncount) {
		int actualbuttoncount = pom.getregisterpage().get_button_count();
		System.out.println("total buttons in Register page is :" + actualbuttoncount);
		Assert.assertEquals(actualbuttoncount, expectedbuttoncount, "Button count in Register page is not 1");
	}

	@Then("User should be able to see button with text {string} in Register UI")
	public void user_should_be_able_to_see_button_with_text_in_register_ui(String expectedtext) {
		List<String> actualButtons = pom.getregisterpage().getbuttontext();

		Assert.assertTrue(actualButtons.contains(expectedtext),
				"button text mismatch , Expected: " + expectedtext + " but found: " + actualButtons);
		System.out.println("button label present in register ui is:" + actualButtons);
	}

	@Then("User must see labels with text in Register UI")
	public void user_must_see_labels_with_text_in_register_ui(DataTable dataTable) {

		List<String> expectedlabel_names = dataTable.asList();

		List<String> actuallabel_names = pom.getregisterpage().getRegisterlabel_Names();

		Assert.assertEquals(actuallabel_names, expectedlabel_names,
				"Label text mismatch , Expected: " + expectedlabel_names + " but found: " + actuallabel_names);

	}

	@Then("User must see {string} company name in top nav bar of  Register UI")
	public void user_must_see_company_name_in_top_nav_bar_of_register_ui(String expectedFromFeature) {

		String actualCompanyName = pom.getregisterpage().getcompanyString().trim();

		Assert.assertEquals(actualCompanyName, expectedFromFeature,
				"Company name mismatch! Expected: " + expectedFromFeature);

		System.out.println("Company name validated correctly");
	}

	@Then("User must see list items for password entry field in Register UI")
	public void user_must_see_list_items_for_password_entry_field_in_register_ui() throws IOException {

		List<Map<String, String>> passwordrules = ExcelReader.readDataFromExcel(filePath, sheetName);

		List<String> actualList = pom.getregisterpage().getPasswordRequirementsText();

		for (Map<String, String> row : passwordrules) {

			String expectedList = row.get("expected_result").trim();

			Assert.assertTrue(actualList.contains(expectedList), "password rule missing.Expected: " + expectedList);
		}

		System.out.println("All password rules are checked");

	}

}
