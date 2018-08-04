package imps;

import dao.FileInformation;
import dao.VehicleData;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.SystemDirectoryService;
import util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SystemDirectory implements SystemDirectoryService {

    private static final Logger logger = Logger.getLogger(SystemDirectory.class);
    List<FileInformation> filesDetail = new ArrayList<FileInformation>();

    private File vehicleDataFileFileName;

    public void scanDirectory(String directoryName, int minimumFiles, String[] mimeTypes, String vehicleDataFileName) {
        List<File> files = FileUtil.reteriveOnlyFiles(directoryName);
        if (files!=null) {
            logger.info("Total files found : " + files.size());
            if (files.size() >= minimumFiles) {
                int countIgnoredFiles = 0;
                for (File file : files) {
                    if (Arrays.asList(mimeTypes).contains(FileUtil.getFileMimeType(file))) {
                        FileInformation FileInformation = new FileInformation();
                        FileInformation.setFileName(file.getName());
                        FileInformation.setFileSize(file.length());
                        FileInformation.setFileExtension(FileUtil.getFileExtension(file));
                        FileInformation.setFileMimeType(FileUtil.getFileMimeType(file));
                        filesDetail.add(FileInformation);
                        if (vehicleDataFileName!=null) {
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
        try {
            FileInputStream file = new FileInputStream(new File(vehicleDataFileFileName.getAbsolutePath()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowNumber = 0;
            List<VehicleData> listOfVehicleData = new ArrayList<VehicleData>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                if (rowNumber > 0) {
                    VehicleData vehicleData = new VehicleData();
                    int cellNumber=0;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                switch (cellNumber) {
                                    case 0:
                                        vehicleData.setRegistrationNumber(cell.getStringCellValue());
                                        break;
                                    case 1:
                                        vehicleData.setMake(cell.getStringCellValue());
                                        break;
                                    case 2:
                                        vehicleData.setColour(cell.getStringCellValue());
                                        break;
                                    case 3:
                                        vehicleData.setFuelType(cell.getStringCellValue());
                                        break;
                                }
                                break;
                        }
                        cellNumber+=1;
                    }
                    listOfVehicleData.add(vehicleData);
                }
                rowNumber += 1;
            }
            file.close();
            return listOfVehicleData;
        } catch (Exception e) {
            logger.error("Vehicle data exception : " + e);
        }
        return null;
    }
}
