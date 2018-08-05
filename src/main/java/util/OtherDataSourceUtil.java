package util;

import dao.VehicleData;
import imps.CSVDataSource;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OtherDataSourceUtil {
    private static final Logger logger = Logger.getLogger(OtherDataSourceUtil.class);

    public static List<VehicleData> getAllVehicleDataFromCSV(File fileName){
        List<VehicleData> listOfVehicleData = new ArrayList<VehicleData>();
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(fileName.getAbsolutePath()));
            csvReader.readLine();
            String line = "";
            while ((line = csvReader.readLine()) != null) {
                final String[] country = line.split(",");
                if (country.length > 0) {
                    VehicleData vehicleData = new VehicleData(){
                        {setRegistrationNumber(country[0]);}
                        {setMake(country[1]);}
                        {setColour(country[2]);}
                        {setFuelType(country[3]);}
                    };
                    listOfVehicleData.add(vehicleData);
                }
            }
            return listOfVehicleData;
        } catch (Exception e) {
            logger.error("CSV exception :" + e);
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    logger.error("CSV exception while closing buffer reader object:" + e);
                }
            }
        }
        return null;
    }
}
