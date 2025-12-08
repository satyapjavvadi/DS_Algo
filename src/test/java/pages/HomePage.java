package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

	private WebDriver driver;

	// this belongs to launch page
//	@FindBy(linkText = "Get Started")
	//WebElement getStartedBtn;

	@FindBy(linkText = "NumpyNinja")
	WebElement companyName;

	@FindBy(xpath = "//a[text()=' Register ']")
	WebElement registerLink;

	@FindBy(xpath = "//a[text()='Sign in']")
	WebElement logInLink;

	@FindBy(xpath = "//input[@name='username']")
	WebElement userName;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[text()='Data Structures']")
	WebElement dataStructureDropdown;
	// all options in data structure dropdown
	@FindBy(xpath = "//a[@class='dropdown-item']")
	List<WebElement> dataStructureOptions;

	@FindBy(tagName = "h4")
	WebElement headingTitle;

	@FindBy(xpath = "//a[text()='Stack']")
	WebElement stackOption;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement ErrMsg;

	@FindBy(xpath = "//div[@class='card-body d-flex flex-column']")
	private List<WebElement> parentCard;
	

	//@FindBy(xpath = "//div[@class='bs-example']")
	//private List<WebElement> parentCardPage;
	
	@FindBy(xpath = "//a[text()=' Mamta.chavan0785@gmail.com ']")
	WebElement loggedInUser;
	
	@FindBy(xpath = "//a[text()='Sign out']")
	WebElement signOutLink;
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void launchUrl(String baseURL) {
		driver.get(baseURL);
	}

	//public void clickGetStarted() {
		//getStartedBtn.click();
	//}

	public String getCompanyName() {
		return companyName.getText();
	}

	public String getRegisterLink() {
		return registerLink.getText();

	}

	public String getLogInLink() {
		return logInLink.getText();
	}
	
	public String getLoggedInUser() {
		return loggedInUser.getText();
	}
	
	public String getSignOutLink() {
		return signOutLink.getText();
	}

	public void clickDataStructureDropdown() {
		dataStructureDropdown.click();
	}

	public List<String> getDataStructureOptionsText() {
		List<WebElement> options = dataStructureOptions;
		System.out.println("dropdown options are:" + options.size());

		for (WebElement option : options) {
			System.out.println(option.getText());
		}
		return options.stream().map(WebElement::getText).collect(Collectors.toList());

	}

	public void clickStackOption() {
		dataStructureDropdown.click();
		stackOption.click();

	}

	public String getErrMsg() {
		return ErrMsg.getText();
	}

	/*public void clickArrayOption() {
		dataStructureDropdown.click();
		arrayOption.click();
	}

	public void clickQueueOption() {
		dataStructureDropdown.click();
		queueOption.click();
	}

	public void clickTreeOption() {
		dataStructureDropdown.click();
		treeOption.click();
	}

	public void clickGraphOption() {
		dataStructureDropdown.click();
		graphOption.click();
	}
*/
	public void enterSignIn() {
		logInLink.click();
		userName.sendKeys("mamta.chavan0785@gmail.com");
		password.sendKeys("Chakuli123$");
		loginBtn.click();
	}

	public void selectOption(String option) {
		dataStructureDropdown.click();
		for (WebElement opt : dataStructureOptions) {
			if (opt.getText().equals(option)) {
				opt.click();
				break;
			}
		}
	}

	public String getHeadingTitle() {
		return headingTitle.getText();
	}

	public List<String> getTitles(String string2) {
		List<String> title = new ArrayList<>();
		for (WebElement child : parentCard) {
			List<WebElement> grandChild = child.findElements(By.xpath(".//h5"));

			for (WebElement element : grandChild) {
				title.add(element.getText());
				System.out.println("Titles are: " + title);
			}
		}
		return title;
	}
	
	public String getPageHeading(String OptionName) {

	switch (OptionName) {
	case "Graph":
		headingTitle.getText();
		break;
	case "Array":
		headingTitle.getText();
		break;
	case "Linked List":
		headingTitle.getText();
		break;
	case "Stack":
		headingTitle.getText();
		break;
	case "Queue":
		headingTitle.getText();
		break;
	case "Tree":
		headingTitle.getText();
		break;
	
	default:
		System.out.println("Invalid Heading");
		break;
	}
	System.out.println("Page Heading is: " + headingTitle.getText());
	return headingTitle.getText();
	//return OptionName;
	}
/*	public List<String> getPageTitles(String string2) {
		List<String> title = new ArrayList<>();
		for (WebElement child : parentCardPage) {
			List<WebElement> grandChild = child.findElements(By.xpath(".//h5"));

			for (WebElement element : grandChild) {
				title.add(element.getText());
				System.out.println("Titles are: " + title);
			}
		}
		return title;
	}*/

	public void clickTitlePage(String ExpectedTitle) {
		String topicHeading;
		for (WebElement child : parentCard) {
			List<WebElement> grandChild = child.findElements(By.xpath(".//h5"));

			for (WebElement element : grandChild) {
				topicHeading = element.getText();
				if (topicHeading.equalsIgnoreCase(ExpectedTitle)) {
					element.findElement(By.xpath("..//a")).click();
					return;
				}
			}
		}
	}
}
