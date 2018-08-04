package pom.ui;

import org.apache.log4j.Logger;
import pom.objects.VehicleEnquiryPage;
import pom.objects.VehicleInformationFromDVLAPage;
import selenium.ExtendedSeleniumAPI;

public class DVLAVehicleInformation {
    private final Logger logger = Logger.getLogger(DVLAVehicleInformation.class);

    public VehicleEnquiry loadDVLAVehicleInfoUI(String url) {
        VehicleInformationFromDVLAPage vehicleInfo = new VehicleInformationFromDVLAPage();
        try {
            vehicleInfo.navigateURL(url);
            logger.info("Navigated url : " + url);
            vehicleInfo.clickStartNow();
            logger.info("Clicked StartNow");
            return new VehicleEnquiry();
        } catch (Exception e) {
            logger.error("DVLA Vehicle Info Exception : " + e);
        }
        vehicleInfo.quitDriver();
        return null;
    }
}
