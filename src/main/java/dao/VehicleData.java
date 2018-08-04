package dao;

public class VehicleData {
    private String registrationNumber;
    private String make;
    private String colour;
    private String fuelType;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String number) {
        this.registrationNumber = number;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

}
