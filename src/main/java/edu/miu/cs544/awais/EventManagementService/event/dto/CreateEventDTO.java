package edu.miu.cs544.awais.EventManagementService.event.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class CreateEventDTO {
    @NotEmpty(message = "Event Name must not be empty")
    private String name;

    @NotEmpty(message = "Event Description must not be empty")
    private String description;

    @NotNull(message = "Event Date must not be null")
    private LocalDateTime date;

    @NotNull(message = "Total seats cannot be null")
    @Min(value = 6, message = "Total seats must be greater than 5")
    private Integer totalSeats;

    @NotNull(message = "Location ID cannot be null")
    @Min(value = 1, message = "Location ID must be greater than 0")
    private Long locationId;

    @NotNull(message = "Category ID cannot be null")
    @Min(value = 1, message = "Category ID must be greater than 0")
    private Long categoryId;

    @NotNull(message = "Staff IDs cannot be null")
    @Size(min = 1, message = "There must be at least one staff member")
    private List<Long> staffIds;

    public CreateEventDTO() {
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }
}
