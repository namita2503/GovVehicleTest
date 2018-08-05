package config;

import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

    private final Logger logger = Logger.getLogger(ReadConfig.class);

    private String validDirectoryName;
    private String invalidDirectoryName;
    private String testDataPath;
    private String[] mimeTypes;

    private int validMinimumFilesInDirectory;
    private int invalidValueForMinimumFiles;

    private String vehicleInformationUrl;
    private InputStream inputStream;

    private String excelDataSource;
    private String csvDataSource;

    private static ReadConfig instance;

    public static ReadConfig getInstance() {
        if (instance == null) {
            try {
                instance = new ReadConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public ReadConfig() throws IOException {
        try {
            Properties config = new Properties();
            String fileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                config.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file " + fileName + " not found in the classpath");
            }
            testDataPath = config.getProperty("Test.Data.Dir");
            validDirectoryName = config.getProperty("Directory.Name");
            invalidDirectoryName = validDirectoryName + config.getProperty("Append.Value.For.Invalid.Directory");
            validMinimumFilesInDirectory = Integer.parseInt(config.getProperty("Min.Files.In.Directory"));
            invalidValueForMinimumFiles = validMinimumFilesInDirectory + Integer.parseInt(config.getProperty("Add.Value.In.Min.Files"));
            mimeTypes = config.getProperty("Mime.Type").split(",");
            vehicleInformationUrl = config.getProperty("DVLA.Vehicle.Information.URL");
            excelDataSource = config.getProperty("Excel.DataSource");
            csvDataSource = config.getProperty("CSV.DataSource");
        } catch (Exception e) {
            logger.error("Exception : " + e);
        } finally {
            inputStream.close();
        }
    }

    public String getValidDirectoryName() {
        return validDirectoryName;
    }

    public int getValidMinimumFilesInDirectory() {
        return validMinimumFilesInDirectory;
    }

    public String getInvalidDirectoryName() {
        return invalidDirectoryName;
    }

    public int getInvalidValueForMinimumFiles() {
        return invalidValueForMinimumFiles;
    }

    public String[] getMimeTypes(){
        return mimeTypes;
    }

    public String getTestDataPath(){
        return testDataPath;
    }

    public String getDVLAVehicleInformationUrl(){
        return vehicleInformationUrl;
    }

    public String getExcelDataSource(){
        return excelDataSource;
    }

    public String getCsvDataSource(){
        return csvDataSource;
    }
}
