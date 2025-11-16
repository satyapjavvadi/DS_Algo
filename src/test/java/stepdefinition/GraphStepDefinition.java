package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DriverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Graph;
import pages.PageObjectManager;

public class GraphStepDefinition {
private PageObjectManager pom;
WebDriver driver;
private Graph graph;

public GraphStepDefinition() {
pom = new PageObjectManager();
driver = DriverFactory.getDriver();
graph = pom.getGraph();

}	

@Given("The user is on the Graph page")
public void the_user_is_on_the_graph_page() {
   graph.clickLogIn();
   graph.clickGraphGetStarted();
   
}

@Given("The user is in the Graph page")
public void the_user_is_in_the_graph_page() {
   graph.clickLogIn();
   graph.clickGraphGetStarted();
}

@When("User scrolls down the page")
public void user_scrolls_down_the_page() {
   
}
@Then("{string} under the topics covered should be visible and clickable")
public void under_the_topics_covered_should_be_visible_and_clickable(String string) {
graph.isTopicEnabled(string);
graph.isTopicDisplayed(string);

//	graph.isTopicVisible(string);
	//graph.isTopicClickable(string);
	}

@When("The Graph page loads")
public void the_graph_page_loads() {
   Assert.assertTrue(driver.getTitle().contains("Graph"));
}


@When("The user clicks the Practice Questions button")
public void the_user_clicks_the_practice_questions_button() {
	graph.clickTopicUrl("Graph");
	graph.clickPracticeQuestionsLink();
}

@Then("The user should be redirected to list of Practice Questions of Graph page.")
public void the_user_should_be_redirected_to_list_of_practice_questions_of_graph_page() {
 Assert.assertTrue(graph.getPracticeQuestionsList().contains("Practice Questions"));
}

@When("The user clicks {string} page")
public void the_user_clicks_try_here_button_in_page(String string) {
graph.clickTopicUrl(string);

}

@Then("Try here>>> button should be visible and clickable below the {string} content")
public void try_here_button_should_be_visible_and_clickable_below_the_content(String string) {
graph.isTryHereBtnVisible();
graph.isTryHereBtnClickable();
}

@When("The user clicks Try Here button in {string} in page")
public void the_user_clicks_try_here_button_in_in_page(String string) {
	graph.clickTopicUrl(string);
	graph.clickTryherebtn();
}

@When("The user clicks {string} button in {string} page")
public void the_user_clicks_button_in_page(String string, String string2) {
 
}
@Then("{string} button should be visible and clickable below the {string} content")
public void button_should_be_visible_and_clickable_below_the_content(String string, String string2) {
 
}

@Given("User is in tryEditor page of {string}")
public void user_is_in_try_editor_page_of(String string) {
	graph.clickLogIn();
	graph.clickGraphGetStarted();
	graph.clickTopicUrl(string);
	graph.clickTryherebtn();
}
@When("User enters {string} code in the Try Editor and clicks on {string} button")
public void user_enters_code_in_the_try_editor_and_clicks_on_button(String code_type, String string2) {
 String valid = "print('Hello')";
 String invalid = "invalid";
	if (code_type.equalsIgnoreCase("valid")) {
	 graph.enterCode(valid);
 }
 else if (code_type.equalsIgnoreCase("invalid")) {
	 graph.enterCode(invalid);
 }
 graph.clickRunButton();

}
@Then("User must see {string} in the UI")
public void user_must_see_in_the_ui(String expected_result) {
if (expected_result.contains("error popup")) {
	String output = graph.getErrorPopupText();
	Assert.assertTrue(output.contains("NameError"));
	System.out.println("Output: " + output);
} else {
	String output1 = graph.getOutputText();
	System.out.println("Output: " + output1);
	Assert.assertEquals(expected_result, output1);
	
}
}

@Then("User must see \"an error popup stating \"NameError: name {string} is not definedon line {int}\"\" in the UI")
public void user_must_see_an_error_popup_stating_name_error_name_is_not_definedon_line_in_the_ui(String expected_result, Integer int1) {
	if (expected_result.contains("error popup")) {
		String output = graph.getErrorPopupText();
		Assert.assertTrue(output.contains("NameError"));
		System.out.println("Output: " + output);
	} else {
		String output = graph.getOutputText();
		Assert.assertEquals(expected_result, output);
		System.out.println("Output: " + output);
	}
}

@When("The user clicks {string} button in Graph in page")
public void the_user_clicks_button_in_graph_in_page(String string) {
 
}

@When("The user clicks Try Here button in Graph page")
public void the_user_clicks_try_here_button_in_graph_page() {
 graph.clickTopicUrl("Graph");
 graph.clickTryherebtn();
 }


@Then("Run button should be visible and clickable")
public void run_button_should_be_visible_and_clickable() {
 graph.isRunBtnVisible();
 graph.isRunBtnClickable();
}

@Given("The user is in the tryEditor page in Graph module")
public void the_user_is_in_the_try_editor_page_in_graph_module() {
graph.clickLogIn();
graph.clickGraphGetStarted();
graph.clickTopicUrl("Graph");
graph.clickTryherebtn();

}

@Given("The user is on the {string} page")
public void the_user_is_on_the_page(String string) {
  
}
@When("The user clicks {string} button in {string} in page")
public void the_user_clicks_button_in_in_page(String string, String string2) {
 
}

@Given("The user is in the Grah page")
public void the_user_is_in_the_grah_page() {
 
}

@When("The user clicks {string} tab under Topics covered")
public void the_user_clicks_tab_under_topics_covered(String string) {
 graph.clickTopic(string);
}


@Then("The user should be redirected to {string} page with related details")
public void the_user_should_be_redirected_to_page_with_related_details(String string) {
 Assert.assertEquals(string, graph.getTopicUrl(string));
 System.out.println("Graph heading: " + string);
}
@Then("the user should be able to see {string} in graph page")
public void the_user_should_be_able_to_see_in_graph_page(String string) {
	Assert.assertEquals(string, graph.getHeading(string));
System.out.println("Graph heading: " + string);
	// Assert.assertEquals(string, dataStructures.getTopicsCoveredText());

} 





}
