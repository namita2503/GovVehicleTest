package util;

import dao.VehicleData;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
    private static final Logger logger = Logger.getLogger(ExcelUtil.class);

    public static List<VehicleData> getAllVehicleDataFromExcel(File fileName){
        List<VehicleData> listOfVehicleData = new ArrayList<VehicleData>();
        try {
            FileInputStream file = new FileInputStream(new File(fileName.getAbsolutePath()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            if (rowIterator.hasNext()) {
                row = rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                VehicleData vehicleData = new VehicleData();
                int cellNumber = 0;
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
                    cellNumber += 1;
                }
                listOfVehicleData.add(vehicleData);
            }
            file.close();
            return listOfVehicleData;
        } catch (Exception e) {
            logger.error("Excel data source exception : " + e);
        }
        return null;
    }
}
