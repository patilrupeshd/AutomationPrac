package stepdef;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.DashboardPage;
import pages.HomePage;
import utility.CommanUtil;

public class LoginSteps {
	HomePage homePage = new HomePage();
	DashboardPage dashboardPage = new DashboardPage();

	@Given("^user launch \"([^\"]*)\" with automation practice application$")
	public void user_launch_with_automation_practice_application(String string) throws Throwable {
		CommanUtil.openBrowser(string);
	}

	@Given("^user provide valid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_provide_valid_and(String username, String passwd) throws Throwable {
		homePage.clickOnSignInButtonWithUserNameAndPassword(username, passwd);
	}

	@Then("^user should be on users dashboard$")
	public void user_should_be_on_users_dashboard() throws Throwable {
		Assert.assertTrue("User is not on Dashboard page", dashboardPage.isDashBoardAppears());

	}

	@Then("^user authentication should failed$")
	public void user_close_the_application() throws Throwable {
		System.out.println(homePage.isAuthenticationFailed() + " Login Page ");
		Assert.assertTrue("Authentication Passed : ", homePage.isAuthenticationFailed());
	}

}
