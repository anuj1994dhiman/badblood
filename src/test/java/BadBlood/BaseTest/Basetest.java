package BadBlood.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public List<HashMap<String,String>> getJsonData() throws IOException {
		File jsonData = new File(System.getProperty("user.dir")+"\\src\\main\\java\\BadBlood\\Utils\\data.json");
		String cred = FileUtils.readFileToString(jsonData, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> credList = mapper.readValue(cred, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return credList;
	}
	
	public Object[][] getExcelData() throws IOException {
		FileInputStream excelFile = new FileInputStream(new File("E:\\Realme 7\\cred.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(excelFile);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colNum = row.getLastCellNum();
		Object[][] credentials = new Object[rowNum-1][colNum];
		DataFormatter dataFormatter = new DataFormatter();
		for(int i=0;i<rowNum-1; i++) {
			XSSFRow r = sheet.getRow(i+1);
			for(int j=0; j<colNum;j++) {
				XSSFCell cel = r.getCell(j);
				credentials[i][j] = dataFormatter.formatCellValue(cel);
			}
		}
		return credentials;
	}
}
