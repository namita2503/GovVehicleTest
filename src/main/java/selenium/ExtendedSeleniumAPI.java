package selenium;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class ExtendedSeleniumAPI {
    private final Logger logger = Logger.getLogger(ExtendedSeleniumAPI.class);
    static WebDriver driver;
    static WebDriverWait wait;
    static {
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 100);
    }

    public WebDriverWait getWaitTime() {
        return wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (getDriver() != null) {
            driver.quit();
            driver = null;
        }
    }

    public void resetDriver() {
        driver.close();
        driver = new FirefoxDriver();
    }

    public void click(WebPage element) {
        driver.findElement(element.getBy(new Object[0])).click();
    }

    public void sendKeys(WebPage element, String value) {
        driver.findElement(element.getBy(new Object[0])).sendKeys(new CharSequence[]{value});
    }

    public String getText(WebPage element) {
        return driver.findElement(element.getBy(new Object[0])).getText();
    }

    public void verifyElementLoaded(WebPage element, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element.getBy(new Object[0])));
        } catch (Exception e) {
            logger.error("Exception " + e);
        }
    }

    public void takeScreenshot(){
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = ".//screenshot";
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (Exception ex) {
            logger.error("Exception taking screenshot", ex);
        }
    }

}
