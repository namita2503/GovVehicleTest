package unittests;

import config.ReadConfig;
import constants.TestDataConst;
import imps.SystemDirectory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SystemDirectoryTests extends SystemDirectory {
    private static final Logger logger = Logger.getLogger(SystemDirectoryTests.class);
    private String validDirectoryName;
    private String invalidDirectoryName;
    private int validMinimumFiles;
    private int invalidMinimumFiles;
    private String[] mimeTypes;

    private void loadTestData(){
        validDirectoryName = TestDataConst.DIRECTORY_NAME;
        invalidDirectoryName = TestDataConst.INVALID_DIRECTORY_NAME;
        validMinimumFiles = TestDataConst.MINIMUM_FILES_IN_DIRECTORY;
        invalidMinimumFiles = TestDataConst.INVALID_MINIMUM_FILES_NUMBER;
        mimeTypes = TestDataConst.SUPPORTED_MIME_TYPES;
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
        printFileData();
        System.out.println();
        logger.info("==========================END TEST SCENARIO==========================");
    }

    private void testScenarioDetails(String testScenarioName, String configuredDirectoryName, int configuredMinFiles, String[] configuredMimeTypes){
        logger.info("TEST SCENARIO : " + testScenarioName);
        System.out.println();
        logger.info("TEST INPUTS ARE:");
        logger.info(">> Configured system directory is : " + configuredDirectoryName);
        logger.info(">> Configured minimum files in system directory is : " + configuredMinFiles);
        logger.info(">> Configured mime types are : " + Arrays.toString(configuredMimeTypes));
        System.out.println();
        logger.info("TEST RESULTS:");
    }

    @Test
    public void printFileInfoFromSystemDirectory() {
        testScenarioDetails("Printing file information from valid system directory", validDirectoryName,validMinimumFiles,mimeTypes);
        scanDirectory(validDirectoryName,validMinimumFiles,mimeTypes,null);
    }

    @Test
    public void invalidSystemDirectory(){
        testScenarioDetails("Invalid system directory",invalidDirectoryName,validMinimumFiles,mimeTypes);
        scanDirectory(invalidDirectoryName,validMinimumFiles,mimeTypes,null);
    }

    @Test
    public void lessThanMinimumFilesInDirectory(){
        testScenarioDetails("File are less than configured minimum files in Directory",validDirectoryName,invalidMinimumFiles,mimeTypes);
        scanDirectory(validDirectoryName,invalidMinimumFiles,mimeTypes,null);
    }

}
