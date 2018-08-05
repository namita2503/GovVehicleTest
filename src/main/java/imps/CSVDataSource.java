package imps;

import dao.VehicleData;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.DataSourceService;
import util.OtherDataSourceUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVDataSource implements DataSourceService {
    private static final Logger logger = Logger.getLogger(CSVDataSource.class);

    public List<VehicleData> reteriveDataFromFile(File vehicleDataFileFileName) {
        List<VehicleData> listOfVehicleData = OtherDataSourceUtil.getAllVehicleDataFromCSV(vehicleDataFileFileName);
        if (listOfVehicleData!=null) {
            logger.info("All data reterived from given data source as CSV");
            return listOfVehicleData;
        }
        logger.error("No data found from given data source as CSV");
        return null;
    }
}