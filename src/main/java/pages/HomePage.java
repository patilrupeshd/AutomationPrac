package pages;

import com.codeborne.selenide.Condition;

import lombok.extern.slf4j.Slf4j;
import utility.CommanUtil;

@Slf4j
public class HomePage extends CommanUtil {

	public HomePage() {

	}

	@Override
	public void waitTillPageLoaded() {
		waitUntilCondition("Sign in", Condition.visible);
		waitUntilCondition("Contact us", Condition.visible);
	}

	public void verifyLoginWithValidCred(String username, String password) {

	}

}
