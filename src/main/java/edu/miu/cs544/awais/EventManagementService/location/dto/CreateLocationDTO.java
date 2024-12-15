package edu.miu.cs544.awais.EventManagementService.location.dto;

public class CreateLocationDTO {

    private String locationName;
    private String street;
    private String city;
    private String state;
    private String zip;

    public CreateLocationDTO() {
    }

    public String getLocationName() {
        return locationName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}

