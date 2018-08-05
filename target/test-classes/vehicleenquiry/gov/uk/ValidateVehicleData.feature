Feature: User will verify vehicle details by giving registration number

  Background: Scan directory for given test data file
    Given "VehicleRegistration.xlsx" file found from scan directory
    #Testdata files: VehicleRegistration.csv, VehicleRegistration.xlsx

  Scenario: Validate vehicle details by giving correct vehicle registration number
    Given Vehicle enquiry page is loaded successfully for registration number "SK06 XPU"
    When Entered the valid registration number of the vehicle
    Then Validated all the vehicle details


  Scenario Outline: Validate vehicle details for all vehicle numbers
    Given Vehicle enquiry page is loaded successfully for "<registration_number>"
    When Entered the valid registration number of the vehicle
    Then Validated all the vehicle details
    Examples:
      | registration_number |
      | SK06 XPU            |
      | BK64 AUP            |