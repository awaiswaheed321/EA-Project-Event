package edu.miu.cs544.awais.EventManagementService.location.domain;

import edu.miu.cs544.awais.EventManagementService.event.Event;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emId;

    private String locationName;
    private String street;
    private String city;
    private String state;
    private String zip;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Event> events;

    public Location() {}

    public Location(String locationName, String street, String city, String state, String zip) {
        this.locationName = locationName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}