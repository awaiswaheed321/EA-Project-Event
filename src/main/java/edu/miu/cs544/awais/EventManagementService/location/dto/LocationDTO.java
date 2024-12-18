package edu.miu.cs544.awais.EventManagementService.location.dto;

import edu.miu.cs544.awais.EventManagementService.location.domain.Location;

public class LocationDTO {

    private Long id;
    private String locationName;
    private String street;
    private String city;
    private String state;
    private String zip;

    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        this.id = location.getEmId();
        this.locationName = location.getLocationName();
        this.street = location.getStreet();
        this.city = location.getCity();
        this.state = location.getState();
        this.zip = location.getZip();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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