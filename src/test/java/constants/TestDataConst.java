package constants;

import config.ReadConfig;

public class TestDataConst {
    public static final String DIRECTORY_NAME = ReadConfig.getInstance().getTestDataPath() + ReadConfig.getInstance().getValidDirectoryName();
    public static final String INVALID_DIRECTORY_NAME = ReadConfig.getInstance().getInvalidDirectoryName();
    public static final int MINIMUM_FILES_IN_DIRECTORY = ReadConfig.getInstance().getValidMinimumFilesInDirectory();
    public static final int INVALID_MINIMUM_FILES_NUMBER = ReadConfig.getInstance().getInvalidValueForMinimumFiles();
    public static final String[] SUPPORTED_MIME_TYPES = ReadConfig.getInstance().getMimeTypes();
    public static final String VEHICLE_DVLA_URL = ReadConfig.getInstance().getDVLAVehicleInformationUrl();
}
