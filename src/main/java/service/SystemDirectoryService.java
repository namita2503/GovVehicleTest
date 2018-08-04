package service;

import dao.FileInformation;
import dao.VehicleData;

import java.util.List;

public interface SystemDirectoryService {
    void scanDirectory(String configuredDirectoryName, int configuredMinimumFiles, String[] mimeTypes, String vehicleDataFileName);
    void printFileData();
    List<VehicleData> reteriveVehicleDataFromFile();
}
