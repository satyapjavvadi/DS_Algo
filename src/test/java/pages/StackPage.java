package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StackPage extends BaseTopicPage {

	@FindBy(css = "a[href='/stack/practice']")
	private WebElement practiceBtn;

	public StackPage(WebDriver driver) {
		super(driver);
	}

	public boolean isTopicsCoveredVisible(String expectedText) {
		return driver.getPageSource().contains(expectedText);
	}

	public List<String> getVisibleTopicTexts() {
		List<String> topics = new ArrayList<>();
		for (WebElement link : topicLinks) {
			topics.add(link.getText().trim());
		}
		return topics;
	}

	public void clickPracticeQuestions() {
		if (!driver.getCurrentUrl().contains("/stack")) {
			throw new IllegalStateException("Not on Stack page!");
		}
		practiceBtn.click();
	}
}
