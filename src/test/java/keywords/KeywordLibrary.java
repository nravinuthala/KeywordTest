package keywords;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeywordLibrary {
    private WebDriver driver;

    public KeywordLibrary(WebDriver driver) {
        this.driver = driver;
    }
    
    public void navigate(String url) {
        driver.get(url);
    }

    public void click(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void enterText(String locator, String value) {
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    public String getText(String locator) {
        return driver.findElement(By.xpath(locator)).getText();
    }
    
    public void verifyText(String locator, String value) {
    	String actual = getText(locator);
    	String expected = value;
    	assertEquals(actual, expected);
    }
}
