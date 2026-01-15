package utils;

import java.time.Duration;

import DriverManager.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSUtils extends DriverFactory {

	// Scroll element into view
	public static void scrollIntoView(WebElement element) {
		((JavascriptExecutor) DriverFactory.getDriver())
				.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Scroll to top of page
	public static void scrollToTop() {
		((JavascriptExecutor) DriverFactory.getDriver())
				.executeScript("window.scrollTo(0, 0)");
	}

	// Scroll to bottom of page
	public static void scrollToBottom() {
		((JavascriptExecutor) DriverFactory.getDriver())
				.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// pause(500); // optional small wait for lazy-loaded content
	}

	// Scroll up by given pixels
	public static void scrollUp(int pixels) {
		((JavascriptExecutor) DriverFactory.getDriver())
				.executeScript("window.scrollBy(0, -" + pixels + ")");
	}

	// Scroll to load all topics (scroll down and slightly up to trigger lazy loading)
	public static void scrollToLoadAllTopics() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();

		// Scroll to bottom
		scrollToBottom();

		// Wait until page is fully loaded
		wait.until(d -> js.executeScript("return document.readyState").equals("complete"));

		// Scroll up slightly to trigger lazy loading
		scrollUp(100);
	}

	// Get full inner text of the page
	public static String getPageInnerText() {
		return (String) ((JavascriptExecutor) DriverFactory.getDriver())
				.executeScript("return document.documentElement.innerText;");
	}

	// Click element via JS
	public static void clickElement(WebElement element) {
		((JavascriptExecutor) DriverFactory.getDriver())
				.executeScript("arguments[0].click();", element);
	}
}
