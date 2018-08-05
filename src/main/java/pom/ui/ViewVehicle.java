package pom.ui;

import org.apache.log4j.Logger;
import pom.objects.ViewVehiclePage;

public class ViewVehicle {
    private final Logger logger = Logger.getLogger(ViewVehicle.class);

    public boolean isValidated(String vehicleNumber, String make, String colour, String fuelType){
        ViewVehiclePage viewVehicle = new ViewVehiclePage();
        try {
            if (viewVehicle.isRegistrationNumberCorrect(vehicleNumber)) {
                if ((viewVehicle.isMakeCorrect(make)) && (viewVehicle.isColourCorrect(colour))) {
                    if ((viewVehicle.isFuelTypeCorrect(fuelType))) {
                        logger.info("Validated vehicle details are:");
                        logger.info("> Vehicle number : " + vehicleNumber);
                        logger.info("> Make : " + make);
                        logger.info("> Colour : " + colour);
                        logger.info("> Fuel type : " + fuelType);
                        viewVehicle.quitDriver();
                        return true;
                    } else {
                        logger.error("Vehicle Fuel type didn't match");
                    }
                } else {
                    logger.error("Vehicle Make or Colour didn't match");
                }
            } else {
                logger.error("Vehicle Registration number didn't match");
            }
        } catch (Exception e) {
            logger.error("View Vehicle Exception : " + e);
        }
        viewVehicle.quitDriver();
        return false;
    }
}
