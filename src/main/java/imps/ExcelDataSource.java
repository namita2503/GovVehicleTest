package imps;

import dao.VehicleData;
import org.apache.log4j.Logger;
import service.DataSourceService;
import util.ExcelUtil;

import java.io.File;
import java.util.List;

public class ExcelDataSource implements DataSourceService {
    private static final Logger logger = Logger.getLogger(ExcelDataSource.class);

    public List<VehicleData> reteriveDataFromFile(File vehicleDataFileFileName) {
        List<VehicleData> listOfVehicleData = ExcelUtil.getAllVehicleDataFromExcel(vehicleDataFileFileName);
        if (listOfVehicleData!=null) {
            logger.info("All data reterived from given data source as Excel");
            return listOfVehicleData;
        }
        logger.error("No data found from given data source as Excel");
        return null;
    }

}