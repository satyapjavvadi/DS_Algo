package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.ToolTipManager;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	private WebDriver driver;

	public RegisterPage( ) {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);

	}

//locators

	@FindBy(xpath = "//a[contains(text(),'NumpyNinja')]")
	private WebElement companyname;

	@FindBy(xpath = "//a[contains(text(),'Register')]")
	private WebElement Registerlink;

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	private WebElement Signinlink;

	@FindBy(xpath = "//a[contains(text(),'Login')]")
	private WebElement loginlink;

	@FindBy(id = "id_username")
	private WebElement usernameField;

	@FindBy(id = "id_password1")
	private WebElement passwordField;

	@FindBy(id = "id_password2")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@type='submit']")
	private List<WebElement> buttoncountElements;

	@FindBy(xpath = "//label")
	private List<WebElement> label_list;

	@FindBy(xpath = "//input[@value='Register']")
	private WebElement Registerbtn;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement alertMessage;

	@FindBy(xpath = "//ul/li")
	private List<WebElement> passwordreq_list;

	// Actions

	public void Registerlink_click() {

		Registerlink.click();

	}

	public String Current_link_Check() {

		String actualurl = driver.getCurrentUrl();

		return actualurl;

	}

	public void Register_User(String username, String password, String confirmpassword) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmpassword);
		Registerbtn.click();
	}

	public String Registerpage_errormessage() {

		try {
			if (alertMessage != null && alertMessage.isDisplayed()) {
				String errorMsg = alertMessage.getText().trim();
				if (!errorMsg.isEmpty()) {

					return errorMsg;
				}
			}
		} catch (Exception e) {
			System.out.println("encounter excep " + e);
		}

		// List of form fields to check for validation messages
		List<WebElement> fields = Arrays.asList(usernameField, passwordField, confirmPasswordField);
		for (WebElement field : fields) {
			try {
				if (field != null) {
					String validationMsg = field.getAttribute("validationMessage");
					System.out.println("tooltip msg is " + validationMsg);
					if (validationMsg != null && !validationMsg.isEmpty()) {
						return validationMsg;
					}
				}
			} catch (Exception e) {
				System.out.println("encounter excep in tooltiperror " + e);
			}
		}

		return null;
	}

	// non functional methods

	public int getInputFieldCount() {
		return label_list.size();
	}

	public List<String> getRegisterlabel_Names() {
		List<String> labelstext = new ArrayList<String>();
		for (WebElement label : label_list) {
			labelstext.add(label.getText().trim());
		}
		return labelstext;

	}

	public int get_button_count() {
		return buttoncountElements.size();

	}

	public List<String> getbuttontext() {
		List<String> button_names = new ArrayList<String>();
		for (WebElement button : buttoncountElements) {
			button_names.add(button.getText().trim());
		}
		return button_names;
	}

	public List<String> Registerpagelinktext() {
		List<String> linktextStrings = new ArrayList<String>();

		if (Registerlink.isDisplayed()) {
			linktextStrings.add(Registerlink.getText().trim());
		}
		if (Signinlink.isDisplayed()) {
			linktextStrings.add(Signinlink.getText().trim());
		}

		if (loginlink.isDisplayed()) {
			linktextStrings.add(loginlink.getText().trim());
		}
		return linktextStrings;
	}

	public String getcompanyString() {
		String companytextString = null;
		if (companyname.isDisplayed()) {
			companytextString = companyname.getText().trim();
		}
		return companytextString;
	}

	public List<String> getPasswordRequirementsText() {
		List<String> password_condition_Texts = new ArrayList<>();
		for (WebElement item : passwordreq_list) {
			if (item.isDisplayed()) {
				password_condition_Texts.add(item.getText().trim());
			}
		}
		return password_condition_Texts;
	}

}
