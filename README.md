# GovVehicleTest

## Location of Test data: '\GovVehicleVerification\src\test\resources\testdata'
 1. Default test data localtion is given for scanning system directory
 2. VehicleRegistration.xlsx OR VehicleRegistration.csv files are having list of vehicle data
 3. To change test data, please go to config.properties and modify as per need
 
## Location of Test Results and logs: '\GovVehicle_TestResults_FromLocal'

## How to run tests on local machine:
1. Clone below repo:
>>HTTPS Link: https://github.com/namita2503/GovVehicleTest.git
>>SSH Link: git@github.com:namita2503/GovVehicleTest.git

2. Import this maven project in any JAVA IDE

3. Run Junit tests (of System Directory service layer) from: '\GovVehicleVerification\src\test\java\unittests\SystemDirectoryTests.java'

4. Run BDD(Selenium/Cucumber) tests (of Gov Vehicle website) from: '\GovVehicleVerification\src\test\resources\vehicleenquiry\gov\uk\ValidateVehicleData.feature'
