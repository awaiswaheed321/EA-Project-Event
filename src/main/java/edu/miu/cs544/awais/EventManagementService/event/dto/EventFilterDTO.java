package edu.miu.cs544.awais.EventManagementService.event.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EventFilterDTO {
    private LocalDateTime beforeDate;
    private LocalDateTime afterDate;
    private String name;
    private List<Long> staffIds;
    private Long locationId;
    private Long categoryId;

    public EventFilterDTO() {
    }

    public LocalDateTime getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(LocalDateTime beforeDate) {
        this.beforeDate = beforeDate;
    }

    public LocalDateTime getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(LocalDateTime afterDate) {
        this.afterDate = afterDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
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
}

