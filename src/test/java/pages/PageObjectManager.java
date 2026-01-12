package pages;

public class PageObjectManager {

	private LaunchPage launchPage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private LinkedListPage linkedlistPage;
	private StackPage stackPage;
	private HomePage homePage;

	private ArrayPage arrayPage;
	private QueuePage queuePage;
	private TreePage treePage;
	private PracticeQuestionsPage practicePage;

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

	public RegisterPage getregisterpage() {
		if (registerPage == null) {
			registerPage = new RegisterPage();
		}

		return registerPage;
	}

	public ArrayPage getarraypage() {
		if (arrayPage == null) {
			arrayPage = new ArrayPage();
		}

		return arrayPage;
	}

	public QueuePage getqueuepage() {
		if (queuePage == null) {
			queuePage = new QueuePage();
		}

		return queuePage;
	}

	public TreePage gettreepage() {
		if (treePage == null) {
			treePage = new TreePage();
		}

		return treePage;
	}

	public PracticeQuestionsPage getpracticepage() {
		if (practicePage == null) {
			practicePage = new PracticeQuestionsPage();
		}

		return practicePage;
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
}
