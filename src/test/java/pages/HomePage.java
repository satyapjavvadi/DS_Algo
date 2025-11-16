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
	@FindBy(linkText = "Get Started")
	WebElement getStartedBtn;

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

	@FindBy(xpath = "//div[@style='margin-top: 40px;margin-bottom: 40px;margin-right: 150px;margin-left: 80px;background-color: ;']/h4")
	WebElement headingTitle;

	@FindBy(xpath = "//a[text()='Stack']")
	WebElement stackOption;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement ErrMsg;

	@FindBy(xpath = "//a[text()='Arrays']")
	WebElement arrayOption;

	@FindBy(xpath = "//a[text()='Queue']")
	WebElement queueOption;

	@FindBy(xpath = "//a[text()='Tree']")
	WebElement treeOption;

	@FindBy(xpath = "//a[text()='Graph']")
	WebElement graphOption;

	@FindBy(xpath = "//a[@href='array']")
	WebElement arrayGetStarted;

	@FindBy(xpath = "//a[@href='stack']")
	WebElement stackGetStarted;

	@FindBy(xpath = "//a[@href='queue']")
	WebElement queueGetStarted;

	@FindBy(xpath = "//a[@href='tree']")
	WebElement treeGetStarted;

	@FindBy(xpath = "//a[@href='linked-list']")
	WebElement linkedListGetStarted;

	@FindBy(xpath = "//div[@class='card-body d-flex flex-column']")
	private List<WebElement> parentCard;
	
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

	public void clickGetStarted() {
		getStartedBtn.click();
	}

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

	public void clickArrayOption() {
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

	public void clickArrayGetStarted() {

		arrayGetStarted.click();
	}

	public void clickStackGetStarted() {

		stackGetStarted.click();
	}

	public void clickQueueGetStarted() {

		queueGetStarted.click();
	}

	public void clickTreeGetStarted() {

		treeGetStarted.click();
	}

	public void clickLinkedListGetStarted() {

		linkedListGetStarted.click();
	}

	public void clickHeadings(String Heading) {
		getStartedBtn.click();
		switch (Heading) {
		case "Array":
			arrayGetStarted.click();
			break;
		case "Stack":
			stackGetStarted.click();
			break;
		case "Queue":
			queueGetStarted.click();
			break;
		case "Tree":
			treeGetStarted.click();
			break;
		case "Linked List":
			linkedListGetStarted.click();
			break;
		default:
			System.out.println("No matching heading found");

		}
	}

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

	public void getTitlePage(String ExpectedTitle) {
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
