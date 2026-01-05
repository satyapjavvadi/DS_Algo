package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class TreePage {
	private WebDriver driver;
	private static final int TIMEOUT = 20;

	public TreePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// locators

			@FindBy(xpath = "//*[@class='bg-secondary text-white']")
			private List<WebElement> headings;

			@FindBy(xpath = "//a[@class='list-group-item']")
			private List<WebElement> tree_subtopicslinks;

			@FindBy(xpath = "//a[contains(text(),'Try')]")
			private WebElement tryhere_button;

			@FindBy(xpath = "//button[text()='Run']")
			private WebElement run_button;

			@FindBy(xpath = "//pre[@id='output']")
			private WebElement outputconsole;

			@FindBy(xpath = "//form[@id='answer_form']")
			private WebElement coding_area;

			@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
			private WebElement Practicequestionslink;

			@FindBy(xpath = "//div[@class='list-group']")
			private List<WebElement> questionslist;
			
			@FindBy(xpath = "//a[@href='tree']")
			WebElement treeGetStarted;

			// action
		
			public void treeGetStarted() {
				treeGetStarted.click();
			}
			
			public String Treepage_link_Check() {

				String actualurl = driver.getCurrentUrl();

				return actualurl;
			}
			
			public void navigateBack() {
			    driver.navigate().back();
			}

			public List<String> getheadingtext() {
		        WaitUtils.waitForVisibilityOfAll(driver, headings,10);
		        List<String> headingtexts = new ArrayList<>();
		        for (WebElement heading : headings) {
		            headingtexts.add(heading.getText().trim());
		        }
		        return headingtexts;
		    }


			public List<String> subtopiclinks() {
				WaitUtils.waitForVisibilityOfAll(driver, tree_subtopicslinks, 20);
				List<String> subtopiclinks = new ArrayList<String>();
				for (WebElement topiclink : tree_subtopicslinks) {
					if (topiclink.isDisplayed() && topiclink.isEnabled()) {
						subtopiclinks.add(topiclink.getText().trim());
					}
				}
				return subtopiclinks;
			}

			public void clicktopiclink(String topicname) {
				WaitUtils.waitForVisibilityOfAll(driver, tree_subtopicslinks, 10);
				for (WebElement link : tree_subtopicslinks) {
					if (link.getText().trim().equalsIgnoreCase(topicname)) {
						link.click();
						
						return;
					}
				}
				throw new NoSuchElementException("Topic link not found: " + topicname);
			}

			public void scrolltobottom() {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}

			public boolean checktryherebutton_displayed() {
				return WaitUtils.isVisible(driver, tryhere_button, 10);

			}
			
			public void clickTryHereButton() {
				tryhere_button.click();

			}
			
			public boolean isPracticeQuestionLinkVisible() {
				return WaitUtils.isVisible(driver, Practicequestionslink, 10);
			}
			
			public boolean isPracticeQuestionLinkEnabled() {
				return Practicequestionslink.isEnabled();
			}

			public void clickPracticeQuestionsLink() {
				Practicequestionslink.click();
			}

			public boolean isQuestionsListDisplayed() {
			    try {
			        if (questionslist.size() > 0) {
			            for (WebElement q : questionslist) {
			                if (q.isDisplayed()) {
			                    return true;   // At least one question visible
			                }
			            }
			        }
			        return false;
			    } catch (Exception e) {
			        System.out.println("Questions list not found: " + e);
			        return false;
			    }
			}

	
}
