package pages;

import DriverManager.DriverFactory;

public class PageObjectManager {

	private LaunchPage launchPage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private LinkedListPage linkedlistPage;
	private StackPage stackPage;
	private HomePage homePage;
	private DataStructurePage dataStructurePage;
	private GraphPage graphPage;
	

	public LaunchPage getLaunchPage() {
		if (launchPage == null) {
			launchPage = new LaunchPage();
		}
		return launchPage;
	}

	public LoginPage getLoginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage();
		}
		return loginPage;
	}

	public RegisterPage getRegisterPage() {
		if (registerPage == null) {
			registerPage = new RegisterPage();
		}
		return registerPage;
	}

	public LinkedListPage getLinkedListPage() {
		if (linkedlistPage == null) {
			linkedlistPage = new LinkedListPage();
		}
		return linkedlistPage;
	}

	public StackPage getStackPage() {
		if (stackPage == null) {
			stackPage = new StackPage();
		}
		return stackPage;
	}

	public HomePage getHomePage() {
		if (homePage == null) {
			homePage = new HomePage();
		}
		return homePage;
	}
	
	public DataStructurePage getDataStructurePage() {
		if (dataStructurePage == null) {
			dataStructurePage = new DataStructurePage();
		}
		return dataStructurePage;
	}
	
	public GraphPage getGraphPage() {
		if (graphPage == null) {
			graphPage = new GraphPage();
		}
		return graphPage;
	}
}
