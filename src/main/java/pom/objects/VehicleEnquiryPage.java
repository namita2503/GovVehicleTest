package pom.objects;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.ExtendedSeleniumAPI;
import selenium.WebPage;

public class VehicleEnquiryPage extends ExtendedSeleniumAPI {

    private WebPage txtRegistrationNumber = null;
    private WebPage btnContinue = null;
    private WebDriverWait wait= getWaitTime();

    public void initPageComponents() {
        txtRegistrationNumber = new WebPage(WebPage.ElementByType.ID, "Vrm");
        btnContinue = new WebPage(WebPage.ElementByType.NAME, "Continue");
    }

    public void enterRegistrationNumber(String vehicleNumber) {
        if (wait.until(ExpectedConditions.titleIs("Check if a vehicle is taxed and has an MOT"))) {
            initPageComponents();
            verifyElementLoaded(btnContinue, wait);
            sendKeys(txtRegistrationNumber, vehicleNumber);
        }
    }

    public void clickContinue() {
        click(btnContinue);
    }

    public void quiteDriver(){
        quitDriver();
    }
}