package edu.miu.cs544.awais.EventManagementService.domain.event.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class CreateEventDTO {
    @NotEmpty(message = "Event Name must not be empty")
    private String name;

    @NotEmpty(message = "Event Description must not be empty")
    private String description;

    @NotNull(message = "Event Date must not be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Use 'yyyy-MM-dd'.")
    private String date;

    @NotNull(message = "Total seats cannot be null")
    @Min(value = 6, message = "Total seats must be greater than 5")
    private Integer totalSeats;

    @NotNull(message = "Price can not be null")
    @Min(value = 1, message = "Price must be greater than 0.")
    private Double ticketPrice;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
