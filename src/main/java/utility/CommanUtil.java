package utility;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import lombok.extern.slf4j.Slf4j;;

@Slf4j
public abstract class CommanUtil {

	public final static String PAGE_TEXT = "//*[contains(text(),'%s')]";

	private final static String CHROME = "Chrome";
	private final static String DEFAULT_BROWSER = "Chrome";
	private final static String FF = "firefox";
	private final static String CHROME_DRIVER = "webdriver.chrome.driver";
	private final static String CHROME_DRIVER_PATH = "/src/main/java/resourse/chromedriver.exe";
	private final static String FF_DRIVER_PATH = "/src/main/java/resourse/geckodriver.exe";
	private final static String FIREFOX_DRIVER = "webdriver.gecko.driver";
	private final static String PROPERTIES_FILE_PATH = "src/main/java/resourse/";
	private final static String PROPERTY_FILE_NAME = "envDetails.properties";
	private final static String APP_URL_PARA = "url";
	private final static String PAGES_LOCATION = "pages";
	private static String formatSplitText = "(?=.)(?=(\\p{Upper}))";
	public static String OS_TYPS = null;
	public static final String OS_TYPE_WIN = "win";
	public static final String OS_TYPE_LINUX = "nux";
	public static final String USER_DIR = "user.dir";

	static {
		OS_TYPS = System.getProperty("os.name").toLowerCase();
	}

	public abstract void waitTillPageLoaded();

	public static void openBrowser(String browserName) {
		WebDriver driverInstance = null;
		if (CHROME.equalsIgnoreCase(browserName)) {
			driverInstance = getChromeBrowserInstance();
		} else if (FF.equalsIgnoreCase(browserName)) {
			driverInstance = getFireFoxInstance();
		}
		String urlToOpen = ReadProperties.getProperty(PROPERTIES_FILE_PATH + PROPERTY_FILE_NAME, APP_URL_PARA);
		setWebDriver(driverInstance);
		driverInstance.get(urlToOpen);
		driverInstance.manage().window().maximize();
		System.out.println("Browser Launched " + urlToOpen);
		String size = driverInstance.manage().window().getSize().getWidth() + " : "
				+ driverInstance.manage().window().getSize().getHeight();
		System.out.println("Screen_Resolution " + size);
	}

	private static WebDriver getChromeBrowserInstance() {
		LoggingPreferences logPref = new LoggingPreferences();
		logPref.enable(LogType.PERFORMANCE, Level.ALL);
		String chromePath = getChromePath();
		System.setProperty(CHROME_DRIVER, chromePath);
		ChromeOptions options = new ChromeOptions();
//    	options.addArguments("--test-type");
//    	//options.addArguments("--no-sandbox");
//    	options.addArguments("ignore-certificate-errors");
//    	options.addArguments("--disable-dev-scm-usage");
//    	options.addArguments("--process-per-site");
//    	options.addArguments("--process-per-tab");
//    	options.addArguments("--single-process");
//    	options.setAcceptInsecureCerts(true);

		WebDriver chromeDriverInstance = new ChromeDriver();
		return chromeDriverInstance;
	}

	private static String getChromePath() {
		String chromePath = "";
		if (OS_TYPS.contains(OS_TYPE_WIN)) {
			chromePath = getCurrentPath() + CHROME_DRIVER_PATH;
			System.out.println("Chrome path for windows " + chromePath);
		} else if (OS_TYPS.contains(OS_TYPE_LINUX)) {
			chromePath = "";
			System.out.println("Chrome path forh linux " + chromePath);
		} else {
			System.out.println("Os types is not supported");
		}
		return chromePath;

	}

	private static WebDriver getFireFoxInstance() {
		String fireFoxPath = getFirefoxPath();
		System.setProperty(FIREFOX_DRIVER, fireFoxPath);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver firefoxInstance = new FirefoxDriver(capabilities);
		System.out.println("Browser is initialies ");
		return firefoxInstance;

	}

	private static String getFirefoxPath() {
		String firefoxPath = "";
		if (OS_TYPS.contains(OS_TYPE_WIN)) {
			firefoxPath = getCurrentPath() + FF_DRIVER_PATH;
			System.out.println("Firefox path for window " + firefoxPath);
		} else if (OS_TYPS.contains(OS_TYPE_LINUX)) {
			firefoxPath = "";
		} else {
			System.out.println("Os is not supperted ");
		}
		return firefoxPath;

	}

	public static String getCurrentPath() {
		String currentPath = System.getProperty(USER_DIR);
		return currentPath;
	}

	public static void closeDriverInstance() {
		WebDriver driverInstance = getWebDriver();
		if (driverInstance != null) {
			driverInstance.close();
			forceSleep(2000);
			// driverInstance.quit();
			forceSleep(200);
		}
		System.err.println("Driver instance is closed");
	}

	public static void forceSleep(long timeInmillisecound) {
		try {
			Thread.sleep(timeInmillisecound);
		} catch (InterruptedException e) {
			System.err.println("Sleep errpr " + e.getMessage());
		}
	}

	public static String getPageName() {
		String callerClass = new Exception().getStackTrace()[1].getClassName();
		return converToFormattedText(callerClass.replace(PAGES_LOCATION, " ").trim());
	}

	public static String converToFormattedText(String line) {
		String[] caseBaseAttribut = line.split(formatSplitText);
		String locatorAttr = "";
		for (int i = 0; i < caseBaseAttribut.length; i++) {
			if (i == caseBaseAttribut.length - 1)
				locatorAttr = locatorAttr + caseBaseAttribut[i];
			else
				locatorAttr = locatorAttr + caseBaseAttribut[i];
		}
		return locatorAttr;
	}

	public static String getFormattedString(String stringToBeForamtted, Object toBeReplaced) {
		return String.format(stringToBeForamtted, toBeReplaced);
	}

	public boolean checkTextPresent(String textToSearch) {
		String pageName = getPageName();
		String textSearchXpath = getFormattedString(PAGE_TEXT, textToSearch);
		List<SelenideElement> elementesWithGivenText = $$(byXpath(textSearchXpath));
		boolean textPresent = elementesWithGivenText.size() > 0;
		System.err.println("Text Present " + pageName + " : " + textToSearch + " : " + textPresent);
		return textPresent;
	}

	public void waitUntilCondition(String locator, Condition condition) {
		String pageName = getPageName();
		try {
			$(byXpath(locator)).waitUntil(condition, 10);
			System.err.println("Wait till element found " + pageName + " : " + locator);
		} catch (Exception e) {
			System.err.println("Element is not found " + locator + " " + condition + e.getMessage());
		}
	}

	public void waitUntilConditionTimeOut(String locator, Condition condition, long timeOut) {
		String pageName = getPageName();
		try {
			$(byXpath(locator)).waitUntil(condition, timeOut);
			System.out.println("Wait till element found " + pageName + " : " + locator);
		} catch (Exception e) {
			System.err.println(
					"Element wait error " + locator + " " + pageName + " : " + timeOut + " : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		System.err.println(getPageName());
	}

}
