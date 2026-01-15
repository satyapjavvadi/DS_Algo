package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverManager.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelReader;
import utils.JSUtils;
import utils.TestContext;
import utils.WaitUtils;

public class ArrayPage {
    private WaitUtils wait;
    private WebDriver driver;

    // locators
    @FindBy(xpath = "//*[@class='bg-secondary text-white']")
    private List<WebElement> headings;

    @FindBy(xpath = "//a[@class='list-group-item']")
    private List<WebElement> array_subtopicslinks;

    @FindBy(xpath = "//a[contains(text(),'Try')]")
    private WebElement tryhere_button;

    @FindBy(xpath = "//button")
    private WebElement run_button;

    @FindBy(xpath = "//pre[@id='output']")
    private WebElement outputconsole;

    @FindBy(xpath = "//form[@id='answer_form']")
    private WebElement coding_area;

    @FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
    private WebElement Practicequestionslink;

    @FindBy(xpath = "//div[@class='list-group']")
    private List<WebElement> questionslist;

    @FindBy(xpath = "//*[@type='submit']")
    private List<WebElement> submit;

	@FindBy(xpath ="//div[@align='left']")
	private WebElement output;

    // action

    public ArrayPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(this.driver, this);
        wait = new WaitUtils();
    }

    public List<String> getheadingtext() {
        wait.waitForVisibilityOfAll(headings);
        List<String> headingtexts = new ArrayList<String>();
        for (WebElement heading : headings) {
            headingtexts.add(heading.getText().trim());
        }
        return headingtexts;
    }

    public List<String> subtopiclinks() {
        wait.waitForVisibilityOfAll(array_subtopicslinks);
        List<String> subtopiclinks = new ArrayList<String>();
        for (WebElement topiclink : array_subtopicslinks) {
            if (topiclink.isDisplayed() && topiclink.isEnabled()) {
                subtopiclinks.add(topiclink.getText().trim());
            }
        }
        return subtopiclinks;
    }

    public void clickTopicLink(String topicName) {
        wait.waitForVisibilityOfAll(array_subtopicslinks);
        for (WebElement link : array_subtopicslinks) {
            if (link.getText().trim().equalsIgnoreCase(topicName)) {
                JSUtils.scrollIntoView(link);
                wait.waitForClickable(link).click();
                return;
            }
        }
        throw new NoSuchElementException("Topic link not found: " + topicName);
    }

    public boolean checktryherebutton_displayed() {
        return WaitUtils.isVisible(driver, tryhere_button, 10);
    }

    public void clickTryHereButton() {
        JSUtils.scrollIntoView(tryhere_button);
        wait.waitForClickable(tryhere_button).click();
    }

    public boolean isPracticeQuestionLinkVisible() {
        return WaitUtils.isVisible(driver, Practicequestionslink, 10);
    }

    public boolean isPracticeQuestionLinkEnabled() {
        return Practicequestionslink.isEnabled();
    }

    public void clickPracticeQuestionsLink() {
        JSUtils.scrollIntoView(Practicequestionslink);
        wait.waitForClickable(Practicequestionslink).click();
    }

    public List<String> getQuestionsList() {
        try {
            wait.waitForVisibilityOfAll(questionslist);
            return questionslist.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Questions list not found: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void clickProblemLink(String problemName) {
        for (WebElement eachQuestion : questionslist) {
            String questionName = eachQuestion.getText();
            if (questionName.equalsIgnoreCase(problemName)) {
                eachQuestion.click();
                return;
            }
        }

    }

    public boolean getButtonTextAssesmentPage(String buttonText) {
        String button = run_button.getText();
        if (button.equalsIgnoreCase(buttonText)) {
            return true;
        } else return false;

    }

    public boolean isSubmitButtonPresent() {
        if (submit.size() > 0) {
            return true;
        } else return false;
    }

    public void submitProblem() {
        for (WebElement eachType : submit) {
            eachType.click();
            return;
        }
    }

	public String getConsoleOutput(){
        wait.waitForPageLoad();

        String result = wait.waitForCodeMirrorOutput("output", 120);
        System.out.println("Submission result: " + result);
		return result;
	}


}
