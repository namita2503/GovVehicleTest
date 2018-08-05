package vehicleenquiry.gov.uk.bdd.tests;

import constants.TestDataConst;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dao.VehicleData;
import imps.SystemDirectory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pom.ui.ConfirmVehicle;
import pom.ui.DVLAVehicleInformation;
import pom.ui.VehicleEnquiry;
import pom.ui.ViewVehicle;
import service.SystemDirectoryService;

import java.util.ArrayList;
import java.util.List;

public class MyStepdefs {
    private final Logger logger = Logger.getLogger(MyStepdefs.class);

    private String vehicleNumber;
    private String vehicleMake;
    private String vehicleColour;
    private String vehicleFuelType;


    private DVLAVehicleInformation info;
    private VehicleEnquiry enquiry;
    private ConfirmVehicle confirmVehicle;
    private ViewVehicle viewVehicle;
    private SystemDirectoryService systemDirectory;
    private List<VehicleData> listOfVehicles = new ArrayList<VehicleData>();

    public MyStepdefs(){
        info = new DVLAVehicleInformation();
        systemDirectory = new SystemDirectory();
    }

    @Given("^\"([^\"]*)\" file found from scan directory$")
    public void fileFoundFromScanDirectory(String vehicleDataFileName) throws Throwable {
        String validDirectoryName = TestDataConst.DIRECTORY_NAME;
        int validMinimumFiles = TestDataConst.MINIMUM_FILES_IN_DIRECTORY;
        String[] mimeTypes = TestDataConst.SUPPORTED_MIME_TYPES;
        systemDirectory.scanDirectory(validDirectoryName,validMinimumFiles,mimeTypes,vehicleDataFileName);
        systemDirectory.printFileData();
        this.listOfVehicles = systemDirectory.reteriveVehicleDataFromFile();
        Assert.assertNotNull("Given vehicle data file not found",listOfVehicles);
    }

    @Given("^Vehicle enquiry page is loaded successfully for registration number \"([^\"]*)\"$")
    public void vehicleEnquiryPageIsLoadedSuccessfullyForRegistrationNumber(String number) throws Throwable {
        for (VehicleData vehicle: listOfVehicles) {
            if (vehicle.getRegistrationNumber().equals(number)) {
                this.vehicleNumber = vehicle.getRegistrationNumber();
                this.vehicleMake = vehicle.getMake();
                this.vehicleColour = vehicle.getColour();
                this.vehicleFuelType = vehicle.getFuelType();
                enquiry = info.loadDVLAVehicleInfoUI(TestDataConst.VEHICLE_DVLA_URL);
            } else {
                logger.debug("Vehicle number " + vehicle.getRegistrationNumber() + " ignored");
            }
        }
        Assert.assertNotNull("Vehicle enquiry page not loaded",enquiry);
    }

    @When("^Entered the valid registration number of the vehicle$")
    public void enteredTheValidRegistrationNumberOfTheVehicle() throws Throwable {
        confirmVehicle = enquiry.submitVehicleNumber(vehicleNumber);
        Assert.assertNotNull("Confirm vehicle page not loaded",confirmVehicle);
    }

    @Then("^Validated all the vehicle details$")
    public void vehicleDetailsPageIsLoadedSuccessfully() throws Throwable {
        viewVehicle = confirmVehicle.submitVehicleBasicDetail(vehicleNumber,vehicleMake,vehicleColour);
        Assert.assertNotNull("Vehicle basic detail not matched",viewVehicle);
        boolean dataMatched = viewVehicle.isValidated(vehicleNumber,vehicleMake,vehicleColour,vehicleFuelType);
        Assert.assertTrue("Vehicle details are not matched", dataMatched);
    }

    @Given("^Vehicle enquiry page is loaded successfully for \"([^\"]*)\"$")
    public void vehicleEnquiryPageIsLoadedSuccessfullyFor(String number) throws Throwable {
        for (VehicleData vehicle: listOfVehicles) {
            if (vehicle.getRegistrationNumber().equals(number)) {
                this.vehicleNumber = vehicle.getRegistrationNumber();
                this.vehicleMake = vehicle.getMake();
                this.vehicleColour = vehicle.getColour();
                this.vehicleFuelType = vehicle.getFuelType();
                enquiry = info.loadDVLAVehicleInfoUI(TestDataConst.VEHICLE_DVLA_URL);
            } else {
                logger.debug("Vehicle number " + vehicle.getRegistrationNumber() + " ignored");
            }
        }
        Assert.assertNotNull("Vehicle enquiry page not loaded",enquiry);
    }
}