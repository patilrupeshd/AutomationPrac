package pages;

import com.codeborne.selenide.Condition;

import lombok.extern.slf4j.Slf4j;
import utility.CommanUtil;

@Slf4j
public class DashboardPage extends CommanUtil {

	private String accountNameXpath = getFormattedString(PAGE_TEXT_CONTAINS, "Gajanan Patil");

	public DashboardPage() {

	}

	@Override
	public void waitTillPageLoaded() {
		waitUntilCondition(accountNameXpath, Condition.visible);
	}

	public boolean isDashBoardAppears() {
		waitUntilCondition(accountNameXpath, Condition.appear);
		return isDisplayed(accountNameXpath);
	}

}
