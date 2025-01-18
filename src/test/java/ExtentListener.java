import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ExtentListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize the report
    public void onStart(ITestContext context) {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
    }

    public void onFinish(ITestContext context) {
        extent.flush();
        extent.close();
    }

    public void onTestStart(ITestResult result) {
        test = extent.startTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        test.log(com.relevantcodes.extentreports.LogStatus.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.log(com.relevantcodes.extentreports.LogStatus.FAIL, "Test Failed");

        // Capture screenshot on failure
        WebDriver driver = new ChromeDriver(); // Initialize your WebDriver
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + result.getName() + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            test.log(com.relevantcodes.extentreports.LogStatus.FAIL, "Screenshot below: " + test.addScreenCapture(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.log(com.relevantcodes.extentreports.LogStatus.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.log(com.relevantcodes.extentreports.LogStatus.INFO, "Test Failed but within success percentage");
    }
}
