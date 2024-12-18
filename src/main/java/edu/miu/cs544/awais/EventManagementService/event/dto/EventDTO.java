package edu.miu.cs544.awais.EventManagementService.event.dto;

import edu.miu.cs544.awais.EventManagementService.category.dto.CategoryDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.LocationDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.StaffDTO;

import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Integer totalSeats;
    private Integer availableSeats;
    private LocationDTO location;
    private CategoryDTO category;
    private List<StaffDTO> staffs;

    public EventDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<StaffDTO> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffDTO> staffs) {
        this.staffs = staffs;
    }
}
