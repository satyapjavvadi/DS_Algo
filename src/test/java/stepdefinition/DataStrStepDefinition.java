package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import pages.DataStructures;
import pages.HomePage;

public class DataStrStepDefinition {
	private PageObjectManager pom;
	WebDriver driver;
	private DataStructures dataStructures;

	public DataStrStepDefinition() {
		pom = new PageObjectManager();
		driver = DriverFactory.getDriver();
		dataStructures = pom.getDataStructures();
	}

	@Given("The user is in the Data Structures - Introduction page")
	public void the_user_is_in_the_data_structures_introduction_page() {
		dataStructures.getlogin();
		dataStructures.clickDataStructureIntroGetStartedBtn();
	}

	@When("The Data Structures - Introduction page loads")
	public void the_data_structures_introduction_page_loads() {
		Assert.assertTrue(driver.getTitle().contains("Data Structures-Introduction"));

	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String string) {
		Assert.assertEquals(string, dataStructures.getkHeading(string));
		// Assert.assertEquals(string, dataStructures.getTopicsCoveredText());

	}

	@Given("User is in {string} UI")
	public void user_is_in_ui(String string) {
		dataStructures.getlogin();
		dataStructures.clickDataStructureIntroGetStartedBtn();
		dataStructures.clickTimeComplexityLink();
	dataStructures.clickTryherebtn();
	}

	@When("User enters {string} code in the Try Editor and clicks on run button")
	public void user_enters_code_in_the_try_editor_and_clicks_on_run_button(String codeType) {
		dataStructures.clickTryherebtn();
	dataStructures.code();
	
		//	dataStructures.enterCode(codeType);
		dataStructures.clickrunBtn();
System.out.println("Code entered and Run button clicked");
	}

	//@Then("User must see {string} in the UI")
	//public void user_must_see_in_the_ui(String string) {
//Assert.assertEquals(string, dataStructures.getOutputText());
		//System.out.println("Verifying output" + string);
	//	Assert.assertEquals(string, dataStructures.getOutputText());

	//}

	@When("The user clicks {string} button")
	public void the_user_clicks_button(String string) {

	}

	@Then("The user should be redirected to {string} page of Data structures-Introduction")
	public void the_user_should_be_redirected_to_page_of_data_structures_introduction(String string) {

	}

	@Given("The user is in the tryEditor page")
	public void the_user_is_in_the_try_editor_page() {
		dataStructures.getlogin();
		dataStructures.clickDataStructureIntroGetStartedBtn();
		dataStructures.clickTimeComplexityLink();
		dataStructures.clickTryherebtn();
	}

	@When("The user clicks the Run Button without entering the code in the Editor")
	public void the_user_clicks_the_run_button_without_entering_the_code_in_the_editor() {
		dataStructures.clickrunBtn();
	}

	@Then("The user should able to get the error message {string}")
	public void the_user_should_able_to_get_the_error_message(String string) {
		Assert.assertEquals(string, dataStructures.getErrAlert());
	}

	@Then("User must see \"an error popup stating \"NameError: name {string} is not defined on line {int}\"\" in the UI")
	public void user_must_see_an_error_popup_stating_name_error_name_is_not_defined_on_line_in_the_ui(String string,
			Integer int1) {

	}

	@When("The user clicks the {string} button")
	public void the_user_clicks_the_button(String string) {

	}
	
	@When("The user clicks the Practice Questions tab")
	public void the_user_clicks_the_practice_questions_tab() {
	 		dataStructures.clickPracticeQuestionsLink();
	}

	@Then("The user should be redirected to list of Practice Questions of Data structures-Introduction")
	public void the_user_should_be_redirected_to_list_of_practice_questions_of_data_structures_introduction() {
		//	System.out.println(dataStructures.getPracticeQuestionsList().size());
		//Assert.assertTrue(dataStructures.getPracticeQuestionsList().size() > 0);
		Assert.assertTrue(dataStructures.getPracticeQuestionsList().contains("List of Practice Questions"));
	}

	@When("The user clicks Time Complexity button")
	public void the_user_clicks_time_complexity_button() {
		dataStructures.clickTimeComplexityLink();
	}

	@Then("The user should be redirected to Time Complexity page of Data structures-Introduction")
	public void the_user_should_be_redirected_to_time_complexity_page_of_data_structures_introduction() {
		Assert.assertTrue(dataStructures.getTimeComplexityText().contains("Time Complexity"));
		
	}

	@Then("Time Complexity tab should be visible and clickable")
	public void time_complexity_tab_should_be_visible_and_clickable() {
		dataStructures.isTabVisible();
		dataStructures.isTabClickable();
	}

	@Given("The user is in the {string} tab")
	public void the_user_is_in_the_tab(String string) {

	}

	@Then("The user should be redirected to a page having an try Editor with a Run button to test")
	public void the_user_should_be_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
		Assert.assertTrue(dataStructures.getRunBtnText().contains("Run"));
	}
	@Given("The user is in the Time Complexity tab")
	public void the_user_is_in_the_time_complexity_tab() {
		dataStructures.getlogin();
		dataStructures.clickDataStructureIntroGetStartedBtn();
		dataStructures.clickTimeComplexityLink();
	}
	
	@When("The user clicks Try Here button")
	public void the_user_clicks_try_here_button() {
		dataStructures.clickTryherebtn();
	}
	
	@Then("Try here tab should be visible and clickable")
	public void try_here_tab_should_be_visible_and_clickable() {

		dataStructures.isTryHereBtnVisible();
		dataStructures.isTryHereBtnClickable();
	}

}