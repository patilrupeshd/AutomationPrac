package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtil {
	private final static String CHROME = "Chrome";
	private final static String FF = "Ff";
	private final static String CHROME_DRIVER = "webdriver.chrome.driver";
	private final static String CHROME_DRIVER_PATH= "src/main/java/resourse/chromedriver.exe";
	private final static String FF_DRIVER_PATH= "";
	private final static String FIREFOX_DRIVER = "webdriver.firefox.driver";
	private final static String PROPERTIES_FILE_PATH = "src/main/java/resourse/";
	private final static String PROPERTY_FILE_NAME = "envDetails.properties";
	private final static String APP_URL_PARA = "url";
	
	private static WebDriver driverInstance = null;

    public static void openBrowser(String browserName)
    {
        
        if(CHROME.equalsIgnoreCase(browserName))
        {
            System.setProperty(CHROME_DRIVER,CHROME_DRIVER_PATH);
            driverInstance = new ChromeDriver();
        }
        else if (FF.equalsIgnoreCase(browserName))
        {
            System.getProperty(FIREFOX_DRIVER,FF_DRIVER_PATH);
            driverInstance = new FirefoxDriver();
        }
        String url = ReadProperties.getProperty(PROPERTIES_FILE_PATH + PROPERTY_FILE_NAME,APP_URL_PARA);
        driverInstance.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driverInstance.get(url);
        driverInstance.manage().window().maximize();
    }
    
    public static void tearDown()
    {
    	if(driverInstance!=null)
    	{
    		driverInstance.quit();
    	}
    }
 }
