package pages;

import java.util.Arrays;
import java.util.List;

import DriverManager.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ExcelReader;
import utils.TestContext;
import utils.WaitUtils;

public class RegisterPage {

	private WebDriver driver;

	public RegisterPage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	// ===== Locators =====
	@FindBy(xpath = "//a[@class='navbar-brand']")
	private WebElement companyName;

	@FindBy(xpath = "//ul//a")
	private List<WebElement> links;

	@FindBy(id = "id_username")
	private WebElement usernameField;

	@FindBy(id = "id_password1")
	private WebElement passwordField;

	@FindBy(id = "id_password2")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@type='submit']")
	private List<WebElement> buttonElements;

	@FindBy(xpath = "//label")
	private List<WebElement> labelList;

	@FindBy(xpath = "//input[@value='Register']")
	private WebElement registerBtn;

	@FindBy(xpath = "//div[@role='alert']")
	private WebElement alertMessage;

	@FindBy(xpath = "//ul/li")
	private List<WebElement> passwordReqList;

	// ===== Actions =====

	public void register(String method, String scenarioType) {
		TestContext.testData = ExcelReader.getTestData(scenarioType);

		System.out.println(TestContext.testData);
		WaitUtils.waitForVisibility(driver, usernameField, 10);
		usernameField.sendKeys(TestContext.testData.get("username"));

		WaitUtils.waitForVisibility(driver, passwordField, 10);
		passwordField.sendKeys(TestContext.testData.get("password"));

		WaitUtils.waitForVisibility(driver, confirmPasswordField, 10);
		confirmPasswordField.sendKeys(TestContext.testData.get("confirmpassword"));

		if ("submits the register form".equalsIgnoreCase(method.trim())) {
			registerBtn.click();
		} else {
			throw new IllegalArgumentException("Unknown submission method: " + method);
		}
	}

	public void refreshBrowser() {
		driver.navigate().refresh();
	}

	public String getRegisterErrorMessage(String scenarioType) {
		if (scenarioType.toLowerCase().contains("null")) {
			for (WebElement field : Arrays.asList(usernameField, passwordField, confirmPasswordField)) {
				String tooltip = field.getAttribute("validationMessage");
				if (tooltip != null && !tooltip.isEmpty()) {
					return tooltip.trim();
				}
			}
		} else {
			try {
				WaitUtils.waitForVisibility(driver, alertMessage, 10);
				return alertMessage.getText().trim();
			} catch (Exception e) {
				return "NO_ERROR_FOUND";
			}
		}
		return null;
	}

	// ===== Non-functional Helpers =====

	public int getInputFieldCount() {
		return labelList.size();
	}

	public List<String> getRegisterLabelNames() {
		return labelList.stream()
				.map(WebElement::getText)
				.map(String::trim)
				.toList();
	}

	public int getButtonCount() {
		return buttonElements.size();
	}

	public List<String> getButtonText() {
		return buttonElements.stream()
				.map(btn -> {
					String text = btn.getText();
					if (text == null || text.isEmpty()) text = btn.getAttribute("value");
					return text != null ? text.trim() : "";
				})
				.filter(s -> !s.isEmpty())
				.toList();
	}

	public List<String> getRegisterPageLinkText() {
		return links.stream()
				.map(WebElement::getText)
				.map(String::trim)
				.toList();
	}

	public String getCompanyName() {
		return companyName.getText().trim();
	}

	public List<String> getPasswordRequirementsText() {
		return passwordReqList.stream()
				.filter(WebElement::isDisplayed)
				.map(WebElement::getText)
				.map(String::trim)
				.map(text -> text
						.replaceAll("[’']", "")
						.replaceAll("\\.$", "")
				)
				.toList();
	}
}