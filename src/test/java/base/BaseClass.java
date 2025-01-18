package base;

import java.lang.reflect.Constructor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class BaseClass {
    public static WebDriver driver;
    public static ExtentTest test;
	public static ExtentReports report;

    @BeforeClass
    public void setUp() throws Exception {
    	System.setProperty("webdriver.gecko.driver", "C:\\Users\\labuser\\Downloads\\geckodriver-v0.35.0-win32\\geckodriver.exe");
    	driver = new FirefoxDriver();
        driver.manage().window().maximize();
        report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        report.endTest(test);
		report.flush();
    }
}
