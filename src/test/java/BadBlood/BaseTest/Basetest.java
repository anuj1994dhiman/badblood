package BadBlood.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import BadBlood.POM.LandingPage;

public class Basetest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else {
			System.out.println("invalid browser");
		}	
		
		return driver;
	}
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public LandingPage launch(String browser) {
		initDriver(browser);
		landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void terminateBrowser() {
		driver.quit();
	}
	
	public String takeScreenShot(WebDriver driver, String testCaseName) throws IOException {
		TakesScreenshot sc = (TakesScreenshot)driver;
		File src=sc.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("E:\\Realme 7\\Books\\reports\\"+testCaseName+".png"));
		return "E:\\Realme 7\\Books\\reports\\"+testCaseName+".png";
	}
	
	public Object[][] getHashMapData() {
		HashMap<String, String> data1 = new HashMap<String, String>();
		data1.put("email", "anu@example.com");
		data1.put("pass", "Anu@1234");
		
		HashMap<String, String> data2 = new HashMap<String, String>();
		data2.put("email", "anu@example.com");
		data2.put("pass", "Anu@1234");
		
		return new Object[][] {{data1}, {data2}};
	}
}