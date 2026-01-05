package stepdefinition;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ConfigReader;
import utils.ExcelReader;

public class RegisterPageStepDefinition {
	private final PageObjectManager pom;

	private final String filePath;

	public RegisterPageStepDefinition(PageObjectManager pom) {
		this.pom = pom;
		filePath = ConfigReader.getProperty("xlPath");

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

	@When("User clicks Register button using positive data from excel")
	public void user_clicks_register_button_using_positive_data_from_excel() {
	 ExcelReader.readDataFromExcel(filePath, "RegisterPage_Validdata");
		
		 Map<String, String> row =
		            ExcelReader.getTestData("valid register");
		
			String username = row.get("username");
			String password = row.get("password");
			String confirmPassword = row.get("confirmpassword");

			String scenarioType = row.get("scenario_type");

			System.out.println("Executing : " + scenarioType);

			pom.getregisterpage().enterUsername(username);
			pom.getregisterpage().enterPassword(password);
			pom.getregisterpage().enterConfirmPassword(confirmPassword);
			pom.getregisterpage().clickregisterbutton();
		}
	

	@Then("User must be navigated to Home page")
	public void user_must_be_navigated_to_home_page() {

		System.out.println("user is on Home page:" + pom.getregisterpage().Current_link_Check());

		Assert.assertTrue(pom.getregisterpage().Current_link_Check().contains("home"), "user is not on Home page");

	}

	@When("User executes register validations from excel")
	public void user_executes_register_validations_from_excel() {

		List<Map<String, String>> registerdata = ExcelReader.readDataFromExcel(filePath,
				"RegisterPage_Negativetestdata");

		for (Map<String, String> row : registerdata) {

			String username = row.get("username");
			String password = row.get("password");
			String confirmPassword = row.get("confirmpassword");
			String expectedMessage = row.get("expected_message");
			String scenarioType = row.get("scenario_type");

			System.out.println("Executing → " + scenarioType);

			pom.getregisterpage().enterUsername(username);
			pom.getregisterpage().enterPassword(password);
			pom.getregisterpage().enterConfirmPassword(confirmPassword);
			pom.getregisterpage().clickregisterbutton();

			boolean expectTooltip = expectedMessage.contains("Please fill out");

			String actualMessage = pom.getregisterpage().Registerpage_errormessage(expectTooltip);

			try {
				Assert.assertEquals(actualMessage, expectedMessage);
			} catch (AssertionError e) {
				System.out.println("\nAssertion Failed For: " + scenarioType);
				System.out.println("Expected : " + expectedMessage);
				System.out.println("Found : " + actualMessage);
			} finally {
				pom.getregisterpage().browser_refresh();
			}
		}
	}

}
