package BadBlood.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MyExtentReport {
	public static ExtentReports generateExtentReport() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("E:\\Realme 7\\Books\\reports\\index.html");
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		return extentReports;
	}
}
