package pages;

import java.security.PublicKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import DriverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelReader;
import utils.TestContext;
import utils.WaitUtils;

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

	// @FindBy(xpath = "//div[@class='alert alert-primary']")
	@FindBy(css = "div.alert.alert-primary[role='alert']")
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
	/*
	 * public void Register_User(String username, String password, String
	 * confirmpassword) throws InterruptedException { usernameField.clear(); if
	 * (!username.isEmpty()) usernameField.sendKeys(username);
	 * System.out.println("entering username" + username); passwordField.clear(); if
	 * (!password.isEmpty()) passwordField.sendKeys(password);
	 * System.out.println("entering pwd" + password); confirmPasswordField.clear();
	 * if (!confirmpassword.isEmpty())
	 * confirmPasswordField.sendKeys(confirmpassword);
	 * System.out.println("entering confirm pwd" + confirmpassword);
	 * Registerbtn.click(); Thread.sleep(500); }
	 */
	
	public void enterUsername(String username) {
        WebElement field = WaitUtils.waitForVisibility(driver, usernameField, 10);
        field.clear();
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        if (password != null && !password.isEmpty()) {
            passwordField.clear();
            passwordField.sendKeys(password);
        }
    }
    
    public void enterConfirmPassword(String confirmpassword) {
        if (confirmpassword != null && !confirmpassword.isEmpty()) {
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys(confirmpassword);
        }
    }
            public void clickregisterbutton() {
            	Registerbtn.click();
            	
            }
            
        
    


	public String Registerpage_errormessage(boolean checkTooltip) {

		// 1. Check HTML5 tooltip only if allowed
		if (checkTooltip) {
			List<WebElement> fields = Arrays.asList(usernameField, passwordField, confirmPasswordField);

			for (WebElement field : fields) {
				try {
					String tooltip = field.getAttribute("validationMessage");
					if (tooltip != null && !tooltip.isEmpty()) {
						return tooltip.trim();
					}
				} catch (Exception ignored) {
				}
			}
		}

		// 2. Check application alert <div>
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement msg = wait.until(ExpectedConditions.visibilityOf(alertMessage));
			return msg.getText().trim();
		} catch (Exception e) {
			return "NO_ALERT_FOUND";
		}
	}
	
	public void login(String method, String scenarioType) {
        TestContext.testData = ExcelReader.getTestData(scenarioType);

        enterUsername(TestContext.testData.get("username"));
        enterPassword(TestContext.testData.get("password"));
        enterConfirmPassword(TestContext.testData.get("confirmpassword"));

        switch (method.toLowerCase().trim()) {
            case "submits the register form":
            case "initiates register":
            case "submits the register form with mouse click":
                Registerlink_click();
                break;
            default:
                throw new IllegalArgumentException("Unknown submission method: " + method);
        }
    }

	public void browser_refresh() {
		driver.navigate().refresh();

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

	        String text = button.getText().trim();

	        // If getText() is empty, try value attribute
	        if (text.isEmpty()) {
	            text = button.getAttribute("value");
	        }

	        if (text != null && !text.trim().isEmpty()) {
	            button_names.add(text.trim());
	        }
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
