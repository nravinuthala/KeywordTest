package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import base.BaseClass;
import keywords.KeywordLibrary;
import utils.ExcelUtils;
import constants.Constants;

public class DriverScript extends BaseClass {
    @Test
    public void runTests() throws Exception {
        ExcelUtils excel = new ExcelUtils(Constants.TEST_DATA_FILE_PATH);
        KeywordLibrary keywords = new KeywordLibrary(driver);
        Class<?> keywordlibrary = Class.forName("keywords.KeywordLibrary");
        Constructor<?> cont = keywordlibrary.getConstructor(WebDriver.class);
        Object keywordlib = cont.newInstance(BaseClass.driver);
//        String url = "https://google.com"; 
//        Method navigate =  keywordlibrary.getMethod("navigate", String.class);
//        navigate.invoke(keywordlib, url);
        
        Method navigate =  keywordlibrary.getMethod("navigate", String.class);
        Method click = keywordlibrary.getMethod("click", String.class);
        Method entertext = keywordlibrary.getMethod("enterText", String.class, String.class);
        Method verifytext = keywordlibrary.getMethod("verifyText", String.class, String.class);

        int rowCount = excel.getRowCount(Constants.TEST_CASES_SHEET);
        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header
        	String keyword = excel.getCellData(Constants.TEST_CASES_SHEET, i, Constants.COLUMN_KEYWORD);
            String locator = excel.getCellData(Constants.TEST_CASES_SHEET, i, Constants.COLUMN_LOCATOR);
            String data = excel.getCellData(Constants.TEST_CASES_SHEET, i, Constants.COLUMN_DATA);

             
            switch (keyword.toLowerCase()) {
                case "click":
                	click.invoke(keywordlib, locator);
                	test.log(LogStatus.PASS, "Navigated to the specified URL");
                    break;
                case "entertext":
                    entertext.invoke(keywordlib, locator, data);
                    break;
                case "navigate":
                    navigate.invoke(keywordlib, data);
                    break;
                case "verifytext":
                	verifytext.invoke(keywordlib, locator, data);
                    break;
                default:
                    System.out.println("Invalid action: " + keyword);
            }
            
        }
    }
}
