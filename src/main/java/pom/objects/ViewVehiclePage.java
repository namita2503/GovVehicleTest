package pom.objects;

import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.ExtendedSeleniumAPI;
import selenium.WebPage;

public class ViewVehiclePage extends ExtendedSeleniumAPI {

    private WebPage lblRegistrationNumber = null;
    private WebPage lblMake = null;
    private WebPage lblColour = null;
    private WebPage lblYearOfManufacture = null;
    private WebPage lblFuelType = null;

    private WebDriverWait wait= getWaitTime();

    public ViewVehiclePage() {
        lblRegistrationNumber = new WebPage(WebPage.ElementByType.XPATH, "//*[@id=\"content\"]/div[1]/h1/span");
        lblMake = new WebPage(WebPage.ElementByType.XPATH, "//*[@id=\"content\"]/div[4]/div/ul/li[1]/span[2]/strong");
        lblYearOfManufacture = new WebPage(WebPage.ElementByType.XPATH, "//*[@id=\"content\"]/div[4]/div/ul/li[3]/span[2]/strong");
        lblFuelType = new WebPage(WebPage.ElementByType.XPATH, "//*[@id=\"FuelTypeShown\"]");
        lblColour = new WebPage(WebPage.ElementByType.XPATH, "//*[@id=\"content\"]/div[4]/div/ul/li[9]/span[2]/strong");
    }

    public boolean isRegistrationNumberCorrect(String vehicleNumber) {
        verifyElementLoaded(lblRegistrationNumber, wait);
        return getText(lblRegistrationNumber).equals(vehicleNumber);
    }

    public boolean isMakeCorrect(String make) {
        return getText(lblMake).equals(make);
    }

    public boolean isColourCorrect(String colour) {
        return getText(lblColour).equals(colour);
    }

    public boolean isFuelTypeCorrect(String fuelType) {
        return getText(lblFuelType).equals(fuelType);
    }

    public void quiteDriver(){
        quitDriver();
    }
}
