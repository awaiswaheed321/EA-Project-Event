package edu.miu.cs544.awais.EventManagementService.location.dto;

import jakarta.validation.constraints.NotEmpty;

public class CreateLocationDTO {
    @NotEmpty(message = "Location Name can not be empty")
    private String locationName;
    @NotEmpty(message = "Location Street can not be empty")
    private String street;
    @NotEmpty(message = "Location City can not be empty")
    private String city;
    @NotEmpty(message = "Location State can not be empty")
    private String state;
    @NotEmpty(message = "Location Zip can not be empty")
    private String zip;

    public CreateLocationDTO() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

