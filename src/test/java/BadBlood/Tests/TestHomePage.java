package BadBlood.Tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BadBlood.BaseTest.Basetest;
import BadBlood.BaseTest.MyRetry;

public class TestHomePage extends Basetest{
	@Test(dataProvider = "dataprovider", groups = {"smoke"})
	public void test1(HashMap<String, String> input) {
		landingPage.login(input.get("email"), input.get("pass"));
	}
	@Test(retryAnalyzer = MyRetry.class)
	public void test2() throws InterruptedException {
		landingPage.login("anu@example.com", "Anu@4321");
		Assert.assertEquals(landingPage.getErrorMsg(), "Incorrect email o password.");
	}
	@DataProvider
	public Object[][] dataprovider() {
		Object[][] cred = getHashMapData();
		return cred;
	}

}
