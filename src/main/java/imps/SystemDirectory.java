package imps;

import dao.FileInformation;
import dao.VehicleData;
import org.apache.log4j.Logger;
import service.DataSourceService;
import service.SystemDirectoryService;
import util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemDirectory implements SystemDirectoryService {

    private static final Logger logger = Logger.getLogger(SystemDirectory.class);

    private File vehicleDataFileFileName;
    private DataSourceService dataSource;
    private List<FileInformation> filesDetail = new ArrayList<FileInformation>();
    private List<VehicleData> listOfVehicleData = new ArrayList<VehicleData>();

    public void scanDirectory(String directoryName, int minimumFiles, String[] mimeTypes, String vehicleDataFileName) {
        List<File> files = FileUtil.reteriveOnlyFiles(directoryName);
        if (files != null) {
            logger.info("Total files found : " + files.size());
            if (files.size() >= minimumFiles) {
                int countIgnoredFiles = 0;
                for (final File file : files) {
                    if (Arrays.asList(mimeTypes).contains(FileUtil.getFileMimeType(file))) {
                        FileInformation fileInfo = new FileInformation(){
                            {setFileName(file.getName());}
                            {setFileSize(file.length());}
                            {setFileExtension(FileUtil.getFileExtension(file));}
                            {setFileMimeType(FileUtil.getFileMimeType(file));}
                        };
                        filesDetail.add(fileInfo);
                        if (vehicleDataFileName != null) {
                            if (file.getName().equals(vehicleDataFileName)) {
                                logger.info("Given vehicle data file name found");
                                this.vehicleDataFileFileName = file;
                            }
                        }
                    } else {
                        logger.debug("Ignored file (" + file.getName() + ") due to not supported mime type");
                        countIgnoredFiles += 1;
                    }
                }
                logger.info("Total files ignored due to other mime type: " + countIgnoredFiles);
            } else {
                logger.error("Failed to scan directory due to files (" + files.size() + ") are less than configured size");
            }
        } else {
            logger.error("Failed to scan directory due to directory not found");
        }
    }


    public void printFileData() {
        if (filesDetail.size() > 0) {
            int index = 1;
            for (FileInformation fileDetail : filesDetail) {
                logger.info("PRINTING FILE NUMBER " + index + " (OUT OF " + filesDetail.size() + ") DETAIL:");
                logger.info(">> File name: " + fileDetail.getFileName());
                logger.info(">> File mime type: " + fileDetail.getFileMimeType());
                logger.info(">> File size (bytes): " + fileDetail.getFileSize());
                logger.info(">> File extension: " + fileDetail.getFileExtension());
                index += 1;
            }
        } else {
            logger.error("No file found to print");
        }
    }

    public List<VehicleData> reteriveVehicleDataFromFile() {
        if (vehicleDataFileFileName != null) {
            if (FileUtil.getFileMimeType(vehicleDataFileFileName).equals(FileUtil.MimeType.APPLICATION_EXCEL.toString())) {
                dataSource = new ExcelDataSource();
            } else if (FileUtil.getFileMimeType(vehicleDataFileFileName).equals(FileUtil.MimeType.TEXT_CSV.toString())) {
                dataSource = new CSVDataSource();
            }
            this.listOfVehicleData = dataSource.reteriveDataFromFile(vehicleDataFileFileName);
            return listOfVehicleData;
        }
        logger.error("Given vehicle data file not exists");
        return null;
    }
}