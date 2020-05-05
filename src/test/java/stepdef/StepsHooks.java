package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utility.CommanUtil;

public class StepsHooks {

	@Before
	public void before(Scenario scenario) {
		System.out.println(scenario.getName());
	}

	@After
	public void after(Scenario scenario) {

		System.out.println(scenario.getName() + " : " + scenario.getStatus().toString().toUpperCase());
		CommanUtil.closeDriverInstance();

	}

}
