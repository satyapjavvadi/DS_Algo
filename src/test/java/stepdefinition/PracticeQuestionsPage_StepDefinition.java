
package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import pages.PracticeQuestionsPage;
import pages.TreePage;

public class PracticeQuestionsPage_StepDefinition {
	private final PageObjectManager pom;
	WebDriver driver;
	private PracticeQuestionsPage practicePage;

	public PracticeQuestionsPage_StepDefinition() {
		pom = new PageObjectManager();
		driver = DriverFactory.getDriver();
		practicePage = pom.getpracticepage();
	}

	@Given("Registered user has navigated to Practice Question UI from Array page")
	public void registered_user_has_navigated_to_practice_question_ui_from_array_page() {
		driver.get("https://dsportalapp.herokuapp.com/login");
		pom.getLoginPage().enterUsername("validUser");
		pom.getLoginPage().enterPassword("validPass");
		pom.getLoginPage().clickLoginButton();
		pom.getarraypage().arrayGetStarted();
		pom.getarraypage().clickArraysInPython();
		pom.getarraypage().clickPracticeQuestionsLink();
		System.out.println("user is on Array practice questions page" + pom.getarraypage().Arraypage_link_Check());
		Assert.assertTrue(pom.getarraypage().Arraypage_link_Check().contains("practice"),
				"user is not on Array practice questions page");
	}

	@When("User clicks on {string} link in Practice Questions UI")
	public void user_clicks_on_problem_link_in_practice_questions_ui(String problemName) {
		pom.getpracticepage().clickProblemLink(problemName);
	}

	@Then("User must be navigated to {string} practice question editor which contains a question, try Editor section with Run and Submit buttons")
	public void user_must_be_navigated_to_practice_editor(String problemName) {
		// getting instance of question UI to compare the elements in it
		PracticeQuestionsPage page = pom.getpracticepage();
		
		Assert.assertTrue(page.getDisplayedQuestion(), "question is not displayed");
		

		Assert.assertTrue(page.isRunButtonVisible(), "Run button is not displayed");

		Assert.assertTrue(page.isSubmitButtonVisible(), "Submit button is not displayed");
		
		Assert.assertTrue(page.isCodeEditorVisible(), " Code editor is not displayed");
		
		
	}

}
