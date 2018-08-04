Feature: User will verify vehicle details by giving registration number

  Scenario: Validate vehicle details by giving correct vehicle registration number
    Given "VehicleRegistration.xlsx" file found from scan directory and picked up row 0 data
    And Vehicle enquiry page is loaded successfully
    When Entered the valid registration number of the vehicle
    Then Validated all vehicle details