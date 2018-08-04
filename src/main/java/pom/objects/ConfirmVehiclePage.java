package pom.objects;

import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.ExtendedSeleniumAPI;
import selenium.WebPage;

public class ConfirmVehiclePage extends ExtendedSeleniumAPI {

    private WebPage lblRegistrationNumber = null;
    private WebPage lblMake = null;
    private WebPage lblColour = null;
    private WebPage rdoYes = null;
    private WebPage lblHeader = null;
    private WebDriverWait wait = getWaitTime();
    private WebPage btnContinue = null;

    public ConfirmVehiclePage(){
        lblHeader = new WebPage(WebPage.ElementByType.XPATH,"//*[@id=\"pr3\"]/div/h1");
        lblRegistrationNumber = new WebPage(WebPage.ElementByType.XPATH,"//*[@id=\"pr3\"]/div/ul/li[1]/span[2]");
        lblMake = new WebPage(WebPage.ElementByType.XPATH,"//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong");
        lblColour = new WebPage(WebPage.ElementByType.XPATH,"//*[@id=\"pr3\"]/div/ul/li[3]/span[2]/strong");
        rdoYes = new WebPage(WebPage.ElementByType.ID,"Correct_True");
        btnContinue = new WebPage(WebPage.ElementByType.NAME,"Continue");
    }

    public boolean isRegistrationNumberCorrect(String vehicleNumber) {
        verifyElementLoaded(lblHeader,wait);
        return getText(lblRegistrationNumber).equals(vehicleNumber);
    }

    public boolean isMakeCorrect(String make) {
        return getText(lblMake).equals(make);
    }

    public boolean isColourCorrect(String colour) {
        return getText(lblColour).equals(colour);
    }

    public void selectYes(){
        click(rdoYes);
    }

    public void clickContinue(){
        click(btnContinue);
    }

    public void quiteDriver(){
        quitDriver();
    }
}
