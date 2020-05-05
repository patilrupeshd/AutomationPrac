package pages;

import org.junit.Assert;

import com.codeborne.selenide.Condition;

import lombok.extern.slf4j.Slf4j;
import utility.CommanUtil;
import utility.ReadProperties;

@Slf4j
public class HomePage extends CommanUtil {

	private String signInLinkXpath = getFormattedString(PAGE_TEXT_CONTAINS, "Sign in");
	private String contactUsLinkXpath = getFormattedString(PAGE_TEXT_CONTAINS, "Contact us");
	//
	private String singInWindowTextXpath = getFormattedString(PAGE_TEXT_CONTAINS, "Already registered?");
	private String authenticationFailedTextXpath = getFormattedString(PAGE_TEXT_CONTAINS, "Authentication failed.");

	private String userNameFieldXpath = getFormattedString(PAGE_ELEMENT_ID, "email");
	private String userPassFieldXpath = getFormattedString(PAGE_ELEMENT_ID, "passwd");
	private String signInButtonXpath = getFormattedString(PAGE_ELEMENT_ID, "SubmitLogin");

	private String pageTitle = "Login - My Store";

	public HomePage() {

	}

	@Override
	public void waitTillPageLoaded() {

		waitUntilCondition(signInLinkXpath, Condition.visible);
		waitUntilCondition(contactUsLinkXpath, Condition.visible);
	}

	public void clickOnSignInButtonWithUserNameAndPassword(String username, String passwd) {
		Assert.assertEquals(true, isDisplayed(signInLinkXpath));
		click(signInLinkXpath);

		Assert.assertEquals(getPageTitle(), pageTitle);

		enter(userNameFieldXpath, ReadProperties.getProperty(username));
		enter(userPassFieldXpath, ReadProperties.getProperty(passwd));
		click(signInButtonXpath);
	}

	public boolean isAuthenticationFailed() {
		System.out.println(getTextOfElement(authenticationFailedTextXpath));
		return isDisplayed(authenticationFailedTextXpath);
	}

}
