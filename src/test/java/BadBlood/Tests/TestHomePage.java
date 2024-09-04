package BadBlood.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BadBlood.BaseTest.Basetest;
import BadBlood.BaseTest.MyRetry;

public class TestHomePage extends Basetest{
	@Test(dataProvider = "dataprovider")
	public void test1(String cell1, String cell2 ){
//	public void test1(HashMap<String, String> input) {
//		landingPage.login(input.get("email"), input.get("pass"));
		System.out.println(cell1);
		System.out.println(cell2);
		//System.out.println(cell3);
	}
	@Test(retryAnalyzer = MyRetry.class)
	public void test2() throws InterruptedException {
		landingPage.login("anu@example.com", "Anu@4321");
		Assert.assertEquals(landingPage.getErrorMsg(), "Incorrect email o password.");
	}
	
	@DataProvider
	public Object[][] dataprovider() throws IOException {
		Object[][] cred = getExcelData();
		return cred;
	}
	
//	@DataProvider
//	public Object[][] dataprovider() {
//		Object[][] cred = getHashMapData();
//		return cred;
//	}
	
//	@DataProvider
//	public Object[][] dataprovider() throws IOException {
//		List <HashMap<String, String>> inputData=getJsonData();
//		return new Object[][] {{inputData.get(0)}, {inputData.get(1)}};
//	}

}
