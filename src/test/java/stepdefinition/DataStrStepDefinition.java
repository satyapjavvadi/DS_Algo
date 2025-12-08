package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import pages.DataStructurePage;


public class DataStrStepDefinition {
	private final PageObjectManager pom;
	private final WebDriver driver;
	private DataStructurePage dataStructurePage;

	public DataStrStepDefinition() {
		pom = new PageObjectManager();
		driver = DriverFactory.getDriver();
		//	dataStructures = pom.getDataStructures();
	}

	@Given("The user is in the Data Structures - Introduction page")
	public void the_user_is_in_the_data_structures_introduction_page() {
		pom.getDataStructurePage().getlogin();          //*******
		//dataStructures.getlogin();
		pom.getDataStructurePage().clickDataStructureIntroGetStartedBtn();
	}

	@When("The Data Structures - Introduction page loads")
	public void the_data_structures_introduction_page_loads() {
		Assert.assertTrue(driver.getTitle().contains("Data Structures-Introduction"));

	}

	@Then("the user should be able to see {string}")
	public void the_user_should_be_able_to_see(String string) {
		Assert.assertEquals(string, pom.getDataStructurePage().getHeading(string));
		// Assert.assertEquals(string, dataStructures.getTopicsCoveredText());

	}

	@Given("User is in {string} UI")
	public void user_is_in_ui(String string) {
		pom.getDataStructurePage().getlogin();
		pom.getDataStructurePage().clickDataStructureIntroGetStartedBtn();
		pom.getDataStructurePage().clickTimeComplexityLink();
		pom.getDataStructurePage().clickTryherebtn();
	}

	@When("User enters {string} code in the Try Editor and clicks on run button")
	public void user_enters_code_in_the_try_editor_and_clicks_on_run_button(String codeType) {
		pom.getDataStructurePage().clickTryherebtn();
		pom.getDataStructurePage().code();
	
		//	dataStructures.enterCode(codeType);
		pom.getDataStructurePage().clickrunBtn();
		System.out.println("Code entered and Run button clicked");
	}

	//@Then("User must see {string} in the UI")
	//public void user_must_see_in_the_ui(String string) {
//Assert.assertEquals(string, dataStructures.getOutputText());
		//System.out.println("Verifying output" + string);
	//	Assert.assertEquals(string, dataStructures.getOutputText());

	//}

	@Given("The user is in the tryEditor page")
	public void the_user_is_in_the_try_editor_page() {
		pom.getDataStructurePage().getlogin();
		pom.getDataStructurePage().clickDataStructureIntroGetStartedBtn();
		pom.getDataStructurePage().clickTimeComplexityLink();
		pom.getDataStructurePage().clickTryherebtn();
	}

	@When("The user clicks the Run Button without entering the code in the Editor")
	public void the_user_clicks_the_run_button_without_entering_the_code_in_the_editor() {
		pom.getDataStructurePage().clickrunBtn();
	}

	@Then("The user should able to get the error message {string}")
	public void the_user_should_able_to_get_the_error_message(String string) {
		Assert.assertEquals(string, pom.getDataStructurePage().getErrAlert());
	}
	
	@When("The user clicks the Practice Questions tab")
	public void the_user_clicks_the_practice_questions_tab() {
	 		pom.getDataStructurePage().clickPracticeQuestionsLink();
	}

	@Then("The user should be redirected to list of Practice Questions of Data structures-Introduction")
	public void the_user_should_be_redirected_to_list_of_practice_questions_of_data_structures_introduction() {
		//	System.out.println(dataStructures.getPracticeQuestionsList().size());
		//Assert.assertTrue(dataStructures.getPracticeQuestionsList().size() > 0);
		Assert.assertTrue(pom.getDataStructurePage().getPracticeQuestionsList().contains("List of Practice Questions"));
	}

	@When("The user clicks Time Complexity button")
	public void the_user_clicks_time_complexity_button() {
		pom.getDataStructurePage().clickTimeComplexityLink();
	}

	@Then("The user should be redirected to Time Complexity page of Data structures-Introduction")
	public void the_user_should_be_redirected_to_time_complexity_page_of_data_structures_introduction() {
		Assert.assertTrue(pom.getDataStructurePage().getTimeComplexityText().contains("Time Complexity"));
		
	}

	@Then("Time Complexity tab should be visible and clickable")
	public void time_complexity_tab_should_be_visible_and_clickable() {
		pom.getDataStructurePage().isTabVisible();
		pom.getDataStructurePage().isTabClickable();
	}

	@Given("The user is in the {string} tab")
	public void the_user_is_in_the_tab(String string) {

	}

	@Then("The user should be redirected to a page having an try Editor with a Run button to test")
	public void the_user_should_be_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
		Assert.assertTrue(pom.getDataStructurePage().getRunBtnText().contains("Run"));
	}
	@Given("The user is in the Time Complexity tab")
	public void the_user_is_in_the_time_complexity_tab() {
		pom.getDataStructurePage().getlogin();
		pom.getDataStructurePage().clickDataStructureIntroGetStartedBtn();
		pom.getDataStructurePage().clickTimeComplexityLink();
	}
	
	@When("The user clicks Try Here button")
	public void the_user_clicks_try_here_button() {
		pom.getDataStructurePage().clickTryherebtn();
	}
	
	@Then("Try here tab should be visible and clickable")
	public void try_here_tab_should_be_visible_and_clickable() {

		pom.getDataStructurePage().isTryHereBtnVisible();
		pom.getDataStructurePage().isTryHereBtnClickable();
	}

}