package pom.ui;

import org.apache.log4j.Logger;
import pom.objects.VehicleEnquiryPage;
import pom.objects.VehicleInformationFromDVLAPage;

public class VehicleEnquiry {
    private final Logger logger = Logger.getLogger(VehicleEnquiry.class);

    public ConfirmVehicle submitVehicleNumber(String vehicleNumber) {
        VehicleEnquiryPage enquiry = new VehicleEnquiryPage();
        try {
            enquiry.enterRegistrationNumber(vehicleNumber);
            logger.info("Given vehicle registration number is : " + vehicleNumber);
            enquiry.clickContinue();
            logger.info("Clicked continue to go Confirm Vehicle page");
            return new ConfirmVehicle();
        } catch (Exception e) {
            logger.error("Vehicle Enquiry Exception : " + e);
        }
        enquiry.quitDriver();
        return null;
    }
}
