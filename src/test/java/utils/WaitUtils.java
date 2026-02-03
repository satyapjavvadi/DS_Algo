package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DriverManager.DriverFactory;

public class WaitUtils {
	private final WebDriverWait wait;
	private final WebDriver driver;

	public WaitUtils() {
		this.driver = DriverFactory.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement waitForVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForPageLoad() {
		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}

	public static void waitForTitleContains(WebDriver driver, String titleFragment, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.titleContains(titleFragment));
	}

	public boolean waitForVisibilityOfAll(List<WebElement> elements) {

		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return !elements.isEmpty();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public static void waitForUrlContains(WebDriver driver, String fragment, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.urlContains(fragment));
	}

	public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String getVisibleText(WebDriver driver, WebElement loginAlert, int timeoutSeconds) {
		return waitForVisibility(driver, loginAlert, timeoutSeconds).getText().trim();
	}

	public static boolean isKeywordInHeaders(WebDriver driver, String keyword) {
		String xpath = "//*[self::h1 or self::h2 or self::h3 or self::h4 or self::p]"
				+ "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
				+ keyword.toLowerCase() + "')]";
		List<WebElement> headers = driver.findElements(By.xpath(xpath));
		return !headers.isEmpty();
	}

	// Search for keyword in a list of elements
	public static boolean isKeywordInElements(List<WebElement> elements, String keyword) {
		return elements.stream().anyMatch(el -> el.getText().toLowerCase().contains(keyword.toLowerCase()));
	}

	public static boolean isVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String waitForCodeMirrorOutput(String elementId, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

		WebElement output = wait.until(driver -> {
			WebElement el = driver.findElement(By.id(elementId));
			String text = el.getAttribute("textContent").trim();
			return !text.isEmpty() ? el : null;
		});

		return output.getAttribute("textContent").trim();
	}

	public static void enterCodeInTryEditor(WebDriver driver, String code) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", code);
	}

	public static void clickRunButton(WebDriver driver) {
		WebElement runButton = driver.findElement(By.xpath("//button[text()='Run']"));
		runButton.click();
	}

	public static void validateOutputOrError(WebDriver driver, String expectedOutput, String expectedError) {
		String actual = driver.findElement(By.id("output")).getText().trim();

		if (expectedOutput != null && !expectedOutput.isBlank()) {
			Assert.assertTrue(actual.contains(expectedOutput),
					"Expected output mismatch. Expected: " + expectedOutput + " Actual: " + actual);
		}

		if (expectedError != null && !expectedError.isBlank()) {
			Assert.assertTrue(actual.contains(expectedError),
					"Expected error mismatch. Expected: " + expectedError + " Actual: " + actual);
		}
	}

	public void waitForPageTitle(String expectedTitle) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains(expectedTitle));
	}

}
