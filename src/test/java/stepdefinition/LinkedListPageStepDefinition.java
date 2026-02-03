package stepdefinition;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageObjectManager;
import utils.ElementUtil;
import utils.ExcelReader;
import utils.TestContext;
import utils.WaitUtils;

public class LinkedListPageStepDefinition {
    private final PageObjectManager pom;
    WaitUtils wait = new WaitUtils();

    public LinkedListPageStepDefinition(PageObjectManager pom) {
        this.pom = pom;

    }

    @Then("the user should be able to see {string} in LinkedList page")
    public void the_user_should_be_able_to_see_in_tree_page(String expectedText) {
        List<String> headings = pom.getLinkedListPage().getheadingtext();
        System.out.println(headings);
        boolean found = false;

        for (String heading : headings) {
            if (heading.equalsIgnoreCase(expectedText)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found, "Expected heading '" + expectedText + "' not found. Actual headings: " + headings);
    }

    @Then("the user should be able to see Linked List topics as clickable links under Topics Covered")
    public void the_user_should_be_able_to_see_Linked_List_topics_as_clickable_links_under_topics_covered(DataTable dataTable) {
        List<String> actualSubtopics = pom.getLinkedListPage().subtopiclinks();
        System.out.println("Actual Subtopic links in LinkedList page: " + actualSubtopics);

        List<String> expectedSubtopics = dataTable.asList();
        System.out.println("Expected Subtopic links in LinkedList page: " + expectedSubtopics);

        for (int i = 0; i < expectedSubtopics.size(); i++) {
            Assert.assertEquals(actualSubtopics.get(i).trim().toLowerCase(),
                    expectedSubtopics.get(i).trim().toLowerCase(),
                    "Mismatch in subtopic link texts in LinkedList page: " + dataTable.toString());
        }
    }

    @Then("Linked List {string} content should be visible")
    public void linked_list_content_should_be_visible(String pageurltext) {
        String expectedSlug = pageurltext.toLowerCase().replace(" ", "-");
        Assert.assertTrue(ElementUtil.getURL().contains(expectedSlug),
                "URL does not contain expected text: " + pageurltext);
    }

    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String topicPage) {
        pom.getLinkedListPage().clickTopicLink(topicPage);

        System.out.println("check " + topicPage);
    }

    @When("the user selects {string} topics covered")
    public void the_user_selects_topicss_covered(String topic) {
        pom.getLinkedListPage().clickTopicLink(topic);
    }

    @Then("the Try here>>> button should be visible below the content")
    public void the_try_here_button_should_be_visible_below_the_content() {
        Assert.assertTrue(pom.getLinkedListPage().checktryherebutton_displayed(), " try here button not visible in ");
    }

    @Given("User is on the Linked List subtopic {string} page")
    public void user_is_on_the_tree_subtopic_page(String topicPage) {
        pom.getLinkedListPage().clickTopicLink(topicPage);
        Assert.assertTrue(pom.getLinkedListPage().getheadingtext().contains(topicPage),
                "user is not on Treesubtopic page");
        System.out.println("check " + topicPage);
    }

    @When("the user activates the Try here button in Linked List page")
    public void user_clicks_the_button_in_tree_ui() {
        pom.getLinkedListPage().clickTryHereButton();
    }

    @Then("User should see the code {string} open")
    public void user_must_be_navigated_to_Linked_List_code_editor(String expectedText) {

        Assert.assertTrue(ElementUtil.getURL().contains(expectedText), "user is not on tryeditor screen");

    }

    @When("the user selects Linked List {string} under Topics Covered")
    public void user_clicks_on_link_under_Linked_List_topics_covered(String topicPage) {
        pom.getLinkedListPage().clickTopicLink(topicPage);

    }

    @Then("User must see {string} clickable link displayed in the side navigation bar in Linked List UI")
    public void user_must_see_clickable_link_displayed_in_the_side_navigation_bar_in_Linked_List_ui(String linkText) {
        boolean islinkvisible = pom.getLinkedListPage().isPracticeQuestionLinkVisible();
        Assert.assertTrue(islinkvisible, "Expected link '" + linkText + "' is not visible in side navigation bar");

        boolean islinkenabled = pom.getLinkedListPage().isPracticeQuestionLinkEnabled();
        Assert.assertTrue(islinkenabled, "Expected link '" + linkText + "' is not enabled in side navigation bar");
    }

    @When("the user selects Practice Questions link in Linked List {string}")
    public void user_clicks_on_practice_questions_link_in_Linked_List_ui(String topicPage) {
        pom.getLinkedListPage().clickTopicLink(topicPage);
        pom.getLinkedListPage().clickPracticeQuestionsLink();
    }

    @Then("User must see title as {string} of Linked List")
    public void user_must_see_list_of_questions_in_Linked_List(String pageName) {
        System.out.println("Print Title actual : " + ElementUtil.getTitle() + " Exp " + pageName);
        Assert.assertTrue(ElementUtil.getTitle().toLowerCase().contains(pageName.toLowerCase()),
                "Title mismatch: expected " + pageName + " but found " + ElementUtil.getTitle());

    }

    @Then("User must see list of practice questions of Linked List")
    public void user_must_see_list_of_questions_in_practice_questions_of_Linked_List() {
        List<String> questions = pom.getLinkedListPage().getQuestionsList();
        Assert.assertTrue(!questions.isEmpty(),
                "No questions are displayed in Practice Questions section of Linked List module");

    }

    @When("User clicks on {string} link under Linked List Topics Covered section")
    public void user_clicks_on_link_under_linked_list_topics_covered_section(String topicPage) {
        pom.getLinkedListPage().clickTopicLink(topicPage);

    }

    @Given("User loads Linked List test data")
    public void load_linked_list_data() {
        TestContext.testDataList = ExcelReader.readDataFromExcel("LinkedListPageContent");
    }

    @When("User runs all Linked List Try Editor scenarios")
    public void run_all_scenarios() {

        for (Map<String, String> row : TestContext.testDataList) {

            String topic = row.get("scenario_type");
            String code = row.get("code_snippet");

            // Skip invalid or junk rows
            if (topic == null || topic.trim().isEmpty() || topic.equalsIgnoreCase("Null value in cred")) {
                System.out.println("Skipping invalid topic: " + topic);
                continue;
            }

            pom.getLinkedListPage().goToLinkedListPage();
            wait.waitForPageTitle("Linked List");

            System.out.println("Navigated to LinkedList. Page Title: " + ElementUtil.getTitle());

            pom.getHomePage().clickGetStarted("LinkedList");

            pom.getLinkedListPage().navigateToTryEditor(topic);
            pom.getLinkedListPage().enterCode(code);
            pom.getLinkedListPage().clickRunButton();

            String actual = pom.getLinkedListPage().getOutputText();
            row.put("actual_output", actual);
        }
    }

    @Then("All Linked List Try Editor results must match expected output")
    public void validate_results() {

        for (Map<String, String> row : TestContext.testDataList) {

            String expected = row.get("expected_output");
            String error = row.get("error_message");
            String actual = row.get("actual_output");

            assert actual.contains(expected) || actual.contains(error)
                    : "Mismatch for scenario: " + row.get("scenario_type");
        }
    }

}
