package stepdefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PracticeQuestion_Editor_StepDefinition {

	@Given("Registered user has navigated to Practice Question UI from Array page")
	public void registered_user_has_navigated_to_practice_question_ui_from_array_page() {
        
    }

    @When("User clicks on {string} link in Practice Questions UI")
    public void user_clicks_on_problem_link_in_practice_questions_ui(String problemName) {
        
    }

    @Then("User must be navigated to {string} practice question editor which contains a question, try Editor section with Run and Submit buttons")
    public void user_must_be_navigated_to_practice_editor(String problemName) {
        
    }

    @When("User enters {string} python code and clicks {string} button")
    public void user_enters_python_code_and_clicks_button(String codeType, String buttonType) {
        
    }

    @Then("User must see {string} in the UI")
    public void user_must_see_expected_result_in_the_ui(String expectedResult) {
        
    }
}
