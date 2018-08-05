package unittests;

import config.ReadConfig;
import constants.TestDataConst;
import dao.VehicleData;
import imps.SystemDirectory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.SystemDirectoryService;

import java.util.Arrays;
import java.util.List;

public class SystemDirectoryTests {
    private static final Logger logger = Logger.getLogger(SystemDirectoryTests.class);
    private String validDirectoryName;
    private String invalidDirectoryName;
    private int validMinimumFiles;
    private int invalidMinimumFiles;
    private String csvDataSource;
    private String excelDataSource;
    private String[] mimeTypes;
    private SystemDirectoryService systemDirectory;

    public SystemDirectoryTests(){
        systemDirectory = new SystemDirectory();
    }

    private void loadTestData(){
        validDirectoryName = TestDataConst.DIRECTORY_NAME;
        invalidDirectoryName = TestDataConst.INVALID_DIRECTORY_NAME;
        validMinimumFiles = TestDataConst.MINIMUM_FILES_IN_DIRECTORY;
        invalidMinimumFiles = TestDataConst.INVALID_MINIMUM_FILES_NUMBER;
        mimeTypes = TestDataConst.SUPPORTED_MIME_TYPES;
        csvDataSource = TestDataConst.CSV_DATASOURCE;
        excelDataSource = TestDataConst.EXCEL_DATASOURCE;
    }

    @Before
    public void beginTest(){
        logger.info("==========================BEGIN TEST SCENARIO==========================");
        System.out.println();
        loadTestData();
    }

    @After
    public void endTest(){
        System.out.println();
        systemDirectory.printFileData();
        System.out.println();
        logger.info("==========================END TEST SCENARIO==========================");
    }

    private void testScenarioDetails(String testScenarioName, String configuredDirectoryName, int configuredMinFiles){
        logger.info("TEST SCENARIO : " + testScenarioName);
        System.out.println();
        logger.info("TEST INPUTS ARE:");
        logger.info(">> Configured system directory is : " + configuredDirectoryName);
        logger.info(">> Configured minimum files in system directory is : " + configuredMinFiles);
        logger.info(">> Configured mime types are : " + Arrays.toString(mimeTypes));
        logger.info(">> Configured excel data source : " + excelDataSource);
        logger.info(">> Configured csv data source : " + csvDataSource);
        System.out.println();
        logger.info("TEST RESULTS:");
    }

    @Test
    public void printFileInfoFromSystemDirectory() {
        testScenarioDetails("Printing file information from valid system directory", validDirectoryName,validMinimumFiles);
        systemDirectory.scanDirectory(validDirectoryName,validMinimumFiles,mimeTypes,null);
    }

    @Test
    public void invalidSystemDirectory(){
        testScenarioDetails("Invalid system directory",invalidDirectoryName,validMinimumFiles);
        systemDirectory.scanDirectory(invalidDirectoryName,validMinimumFiles,mimeTypes,null);
    }

    @Test
    public void lessThanMinimumFilesInDirectory(){
        testScenarioDetails("File are less than configured minimum files in Directory",validDirectoryName,invalidMinimumFiles);
        systemDirectory.scanDirectory(validDirectoryName,invalidMinimumFiles,mimeTypes,null);
    }

    @Test
    public void readExcelDataSource(){
        testScenarioDetails("Reteriving test data from excel file",validDirectoryName,validMinimumFiles);
        systemDirectory.scanDirectory(validDirectoryName,validMinimumFiles,mimeTypes,excelDataSource);
        List<VehicleData> listOfVehicleData = systemDirectory.reteriveVehicleDataFromFile();
        Assert.assertNotNull("Couldn't reterive test data from provided file", listOfVehicleData);
        logger.info("Number of vehicles data found : " + listOfVehicleData.size());
    }

    @Test
    public void readCSVDataSource(){
        testScenarioDetails("Reteriving test data from csv file",validDirectoryName,validMinimumFiles);
        systemDirectory.scanDirectory(validDirectoryName,validMinimumFiles,mimeTypes,csvDataSource);
        List<VehicleData> listOfVehicleData = systemDirectory.reteriveVehicleDataFromFile();
        Assert.assertNotNull("Couldn't reterive test data from provided file", listOfVehicleData);
        logger.info("Number of vehicles data found : " + listOfVehicleData.size());
    }

}
