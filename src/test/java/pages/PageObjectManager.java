package pages;

import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;

public class PageObjectManager {

	private final WebDriver driver;
	
	private LaunchPage launchPage;
	private LoginPage loginPage;
    private RegisterPage registerPage;
    private LinkedListPage linkedlistPage;
    private StackPage stackPage;
    private DataStructurePage dataStructurePage;
    private HomePage homePage;
    private GraphPage graphPage;

	public PageObjectManager() {
		this.driver = DriverFactory.getDriver();
		if (this.driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

	}
	
	public DataStructurePage getDataStructurePage() {
		if (dataStructurePage == null) {
			dataStructurePage = new DataStructurePage(driver);
		}
		return dataStructurePage;
	}
	
	public HomePage getHomePage() {
		if (homePage == null) {
			homePage = new HomePage(driver);
		}
		return homePage;
	}
	
	public GraphPage getGraphPage() {
		if (graphPage == null) {
			graphPage = new GraphPage(driver);
		}
		return graphPage;
	}

	public LaunchPage getLaunchPage() {
		if (launchPage == null) {
			launchPage = new LaunchPage(driver);
		}

		return launchPage;
	}
	
	public LoginPage getLoginPage()
	{
		if(loginPage == null)
		{
			loginPage = new LoginPage(driver);
	
		}
		return loginPage;
	}
    
    public RegisterPage getregisterpage() {
        if(registerPage == null){
            registerPage = new RegisterPage(driver);
        }

        return registerPage;
    }
 
    /*
public LinkedListPage getLinkedListPage() {
	if(linkedlistPage == null) {
		linkedlistPage = new LinkedListPage(driver);
	}
   
	return linkedlistPage;
	}

public StackPage getStackPage() {	
	if(stackPage == null) {
		stackPage = new StackPage(driver);
		
	}
	   return stackPage;
	}
*/
}