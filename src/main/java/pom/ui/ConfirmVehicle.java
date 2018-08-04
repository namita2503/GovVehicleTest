package pom.ui;

import org.apache.log4j.Logger;
import pom.objects.ConfirmVehiclePage;

public class ConfirmVehicle {
    private final Logger logger = Logger.getLogger(ConfirmVehicle.class);

    public ViewVehicle submitVehicleBasicDetail(String vehicleNumber, String make, String colour) {
        ConfirmVehiclePage confirmVehicle = new ConfirmVehiclePage();
        try {
            if (confirmVehicle.isRegistrationNumberCorrect(vehicleNumber)) {
                if ((confirmVehicle.isMakeCorrect(make)) && (confirmVehicle.isColourCorrect(colour))) {
                    logger.info("Given vehicle registration details are matched : ");
                    logger.info("> Vehicle number : " + vehicleNumber);
                    logger.info("> Make : " + make);
                    logger.info("> Colour : " + colour);
                    confirmVehicle.selectYes();
                    confirmVehicle.clickContinue();
                    logger.info("Clicked continue to go View Vehicle page");
                    return new ViewVehicle();
                }
            }
        } catch (Exception e) {
            logger.error("Confirm Vehicle Exception : " + e);
        }
        confirmVehicle.quitDriver();
        return null;
    }
}
