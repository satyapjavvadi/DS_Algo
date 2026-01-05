package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

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

	@Then("User must see labels with text in Register UI")
	public void user_must_see_labels_with_text_in_register_ui() throws IOException {

		List<Map<String, String>> registerlabels = ExcelReader.readDataFromExcel(filePath, sheetName);

		List<String> actualLabels = pom.getregisterpage().getRegisterlabel_Names();

		for (Map<String, String> row : registerlabels) {

			if (!row.get("scenario_type").equalsIgnoreCase("Validate labels text in Register UI"))
				continue;

			String expectedLabel = row.get("expected_result").trim();

			Assert.assertTrue(actualLabels.contains(expectedLabel), "Label missing - Expected: " + expectedLabel);
		}

		System.out.println("All label validations passed");
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

	@Then("User must see links with text in Register UI")
	public void user_must_see_links_with_text_in_register_ui() throws IOException {

		List<Map<String, String>> registerlinktext = ExcelReader.readDataFromExcel(filePath, sheetName);

		List<String> actualLinks = pom.getregisterpage().Registerpagelinktext();

		for (Map<String, String> row : registerlinktext) {

			if (!row.get("scenario_type").equalsIgnoreCase("validate links text in Register UI"))
				continue;

			String expectedLink = row.get("expected_result").trim();

			Assert.assertTrue(actualLinks.contains(expectedLink), "Link text missing - Expected: " + expectedLink);
		}

		System.out.println("All link validations passed");
	}

	@Then("User must see {string} company name in top nav bar of  Register UI")
	public void user_must_see_company_name_in_top_nav_bar_of_register_ui(String expectedFromFeature)
			throws IOException {

		String filePath = "src/test/resources/DS_ExcelData.xlsx";
		String sheetName = "RegisterPage_labels";

		List<Map<String, String>> companyname = ExcelReader.readDataFromExcel(filePath, sheetName);

		String expectedFromExcel = "";

		for (Map<String, String> row : companyname) {
			if (row.get("scenario_type").equalsIgnoreCase("company name must be present in nav bar")) {
				expectedFromExcel = row.get("expected_result").trim();
				break;
			}
		}

		String actualCompanyName = pom.getregisterpage().getcompanyString().trim();

		Assert.assertEquals(actualCompanyName, expectedFromExcel,
				"Company name mismatch! Expected: " + expectedFromExcel);

		System.out.println("Company name validated correctly");
	}

	@Then("User must see list items for password entry field in Register UI")
	public void user_must_see_list_items_for_password_entry_field_in_register_ui() throws IOException {

		List<Map<String, String>> passwordrules = ExcelReader.readDataFromExcel(filePath, sheetName);

		List<String> actualList = pom.getregisterpage().getPasswordRequirementsText();

		for (Map<String, String> row : passwordrules) {

			if (!row.get("scenario_type").equalsIgnoreCase("password entry rules"))
				continue;

			String expectedList = row.get("expected_result").trim();

			Assert.assertTrue(actualList.contains(expectedList), "password rule missing - Expected: " + expectedList);
		}

		System.out.println("All password rules are checked");

	}

}
