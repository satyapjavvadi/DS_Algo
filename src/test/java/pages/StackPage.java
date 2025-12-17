package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.WaitUtils;

public class StackPage {

	WebDriver driver;

	@FindBy(css = "a[href='/stack/practice']")
	WebElement practiceBtn;

	@FindBy(css = "a.list-group-item")
	List<WebElement> topicLinks;

	@FindBy(tagName = "body")
	WebElement bodyText;

	public StackPage( ) {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void navigateToStackPage() {
		driver.get(ConfigReader.getProperty("baseURL") + "stack");
		WaitUtils.waitForTitleContains(driver, "Stack", 10);
	}

	public List<String> getVisibleTopicTexts() {
		List<String> topics = new ArrayList<>();
		for (WebElement link : topicLinks) {
			topics.add(link.getText().trim());
		}
		return topics;
	}

	public boolean isSilentFailureDetected() {
		String pageText = bodyText.getText();
		List<String> boilerplate = Arrays.asList("NumpyNinja", "Data Structures", "ValidUser", "Sign out");
		return boilerplate.stream().allMatch(pageText::contains);
	}

	public void assertPageHasContent() {
		boolean silentFailure = isSilentFailureDetected();
		Assert.assertFalse(silentFailure, "Page loaded with boilerplate only — no content or error message displayed.");
	}

	public void assertKeywordsPresent(String pageText, String expectedContent, String topicPage) {
		for (String keyword : expectedContent.toLowerCase().split(",")) {
			if (!pageText.contains(keyword.trim())) {
				Assert.fail("❌ Missing keyword: " + keyword + " on page: " + topicPage);
			}
		}
		System.out.println("✅ Verified scroll content for: " + topicPage);

	}

	public String getPageText() {
		// Wait for presence of body element before returning text
		WaitUtils.waitForVisibility(driver, bodyText, 10);
		return bodyText.getText().toLowerCase();
	}

}
