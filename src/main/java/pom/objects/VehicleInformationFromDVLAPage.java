package pom.objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.ExtendedSeleniumAPI;
import selenium.WebPage;

public class VehicleInformationFromDVLAPage extends ExtendedSeleniumAPI {
    private static final Logger logger = Logger.getLogger(VehicleInformationFromDVLAPage.class);

    private WebPage btnStartNow = null;
    private WebDriverWait wait = getWaitTime();

    public void initPageComponents() {
        btnStartNow = new WebPage(WebPage.ElementByType.XPATH, "//*[@id=\"get-started\"]/a");
    }

    public void navigateURL(String url) {
        getDriver().navigate().to(url);
    }

    public void clickStartNow() {
        if (wait.until(ExpectedConditions.titleIs("Get vehicle information from DVLA - GOV.UK"))) {
            initPageComponents();
            verifyElementLoaded(btnStartNow, wait);
            click(btnStartNow);
        }
    }

    public void quiteDriver(){
        quitDriver();
    }
}

