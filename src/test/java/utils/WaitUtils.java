package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitUtils {
	public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForClickable(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static WebElement waitForClickable(WebDriver driver, WebElement element, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForPageLoad(WebDriver driver, int timeoutSeconds) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
				.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
						.equals("complete"));
	}

	public static void waitForTitleContains(WebDriver driver, String titleFragment, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.titleContains(titleFragment));
	}

	public static void waitForTitleIs(WebDriver driver, String exactTitle, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(ExpectedConditions.titleIs(exactTitle));
	}

	public static boolean waitForVisibilityOfAll(WebDriver driver, List<WebElement> elements, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
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

	public static WebElement waitForPresence(WebDriver driver, By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
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

	// ✅ Enter code into CodeMirror editor
	public static void enterCodeInTryEditor(WebDriver driver, String codeSnippet) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(d -> d.findElement(By.cssSelector(".CodeMirror")).isDisplayed());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", codeSnippet);
		System.out.println("✅ Code injected into CodeMirror: " + codeSnippet);
	}

	// ✅ Click Run button
	public static void clickRunButton(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement runBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Run']")));
		runBtn.click();
	}

	public static void validateOutputOrError(WebDriver driver, String expectedOutput, String errorMessage) {
		try {
			Alert alert = driver.switchTo().alert();
			String actualError = alert.getText();
			alert.accept();
			if (errorMessage != null && !errorMessage.isEmpty()) {
				Assert.assertTrue(actualError.contains(errorMessage),
						"Expected error: " + errorMessage + " but got: " + actualError);
			}
		} catch (NoAlertPresentException e) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement outputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
			String actualOutput = outputElement.getText().trim();

			if (expectedOutput != null && !expectedOutput.isEmpty()) {
				// ✅ Normalize numbers
				if (isNumeric(expectedOutput) && isNumeric(actualOutput)) {
					double expected = Double.parseDouble(expectedOutput);
					double actual = Double.parseDouble(actualOutput);
					Assert.assertEquals(actual, expected, "Numeric output mismatch");
				} else {
					Assert.assertEquals(actualOutput, expectedOutput.trim(), "Output mismatch");
				}
			}
		}
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
