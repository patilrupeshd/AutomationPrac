package stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.CommanUtil;


public class LoginSteps {

    @Given("user lunch {string} with automation practice application")
    public void user_lunch_with_automation_practice_application(String browserName) {
        CommanUtil.openBrowser(browserName);
    }
    
    @And("user close the application")
    public void closetheBrowser()
    {
    	CommanUtil.closeDriverInstance();
	}
}
