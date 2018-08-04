package vehicleenquiry.gov.uk.bdd.tests;

import constants.TestDataConst;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dao.VehicleData;
import imps.SystemDirectory;
import org.junit.Assert;
import pom.ui.ConfirmVehicle;
import pom.ui.DVLAVehicleInformation;
import pom.ui.VehicleEnquiry;
import pom.ui.ViewVehicle;

import java.util.ArrayList;
import java.util.List;

public class MyStepdefs {
    private String vehicleNumber;
    private String vehicleMake;
    private String vehicleColour;
    private String vehicleFuelType;


    DVLAVehicleInformation info;
    VehicleEnquiry enquiry;
    ConfirmVehicle confirmVehicle;
    ViewVehicle viewVehicle;
    SystemDirectory systemDirectory;
    List<VehicleData> listOfVehicles = new ArrayList<VehicleData>();

    public MyStepdefs(){
        info = new DVLAVehicleInformation();
        systemDirectory = new SystemDirectory();
    }

    @Given("^\"([^\"]*)\" file found from scan directory and picked up row (\\d+) data$")
    public void fileFoundFromScanDirectoryAndPickedUpRowData(String vehicleDataFileName, int rowIndex) throws Throwable {
        String validDirectoryName = TestDataConst.DIRECTORY_NAME;
        int validMinimumFiles = TestDataConst.MINIMUM_FILES_IN_DIRECTORY;
        String[] mimeTypes = TestDataConst.SUPPORTED_MIME_TYPES;
        systemDirectory.scanDirectory(validDirectoryName,validMinimumFiles,mimeTypes,vehicleDataFileName);
        systemDirectory.printFileData();
        listOfVehicles = systemDirectory.reteriveVehicleDataFromFile();
        Assert.assertTrue("Vehicle data not found in given file",listOfVehicles.size()>0);
        Assert.assertTrue("Given rowIndex is greater than Vehicle data file rows", rowIndex<listOfVehicles.size());
        this.vehicleNumber = listOfVehicles.get(rowIndex).getRegistrationNumber();
        this.vehicleMake = listOfVehicles.get(rowIndex).getMake();
        this.vehicleColour = listOfVehicles.get(rowIndex).getColour();
        this.vehicleFuelType=listOfVehicles.get(rowIndex).getFuelType();
    }


    @And("^Vehicle enquiry page is loaded successfully$")
    public void vehicleEnquiryPageIsLoadedSuccessfully() throws Throwable {
        enquiry = info.loadDVLAVehicleInfoUI(TestDataConst.VEHICLE_DVLA_URL);
        Assert.assertNotNull("Vehicle enquiry page not loaded",enquiry);
    }

    @When("^Entered the valid registration number of the vehicle$")
    public void enteredTheValidRegistrationNumberOfTheVehicle() throws Throwable {
        confirmVehicle = enquiry.submitVehicleNumber(vehicleNumber);
        Assert.assertNotNull("Confirm vehicle page not loaded",confirmVehicle);
    }

    @Then("^Validated all vehicle details$")
    public void vehicleDetailsPageIsLoadedSuccessfully() throws Throwable {
        viewVehicle = confirmVehicle.submitVehicleBasicDetail(vehicleNumber,vehicleMake,vehicleColour);
        Assert.assertNotNull("Vehicle basic detail not matched",viewVehicle);
        boolean dataMatched = viewVehicle.isValidated(vehicleNumber,vehicleMake,vehicleColour,vehicleFuelType);
        Assert.assertTrue("Vehicle details are not matched", dataMatched);
    }
}