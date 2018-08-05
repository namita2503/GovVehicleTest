package service;

import dao.VehicleData;

import java.io.File;
import java.util.List;

public interface DataSourceService {
    List<VehicleData> reteriveDataFromFile(File vehicleDataFileFileName);
}
