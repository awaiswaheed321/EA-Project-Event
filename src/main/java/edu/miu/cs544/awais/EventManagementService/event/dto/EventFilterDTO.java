package edu.miu.cs544.awais.EventManagementService.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventFilterDTO {
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Use 'yyyy-MM-dd'.")
    private String afterDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Use 'yyyy-MM-dd'.")
    private String beforeDate;
    private String name;
    private Double priceLessThan;
    private Double priceGreaterThan;
    private List<Long> staffIds;
    private Long locationId;
    private Long categoryId;

    public EventFilterDTO() {
    }

    @Override
    public String toString() {
        return "EventFilterDTO{" +
                "beforeDate=" + beforeDate +
                ", afterDate=" + afterDate +
                ", name='" + name + '\'' +
                ", priceLessThan=" + priceLessThan +
                ", priceGreaterThan=" + priceGreaterThan +
                ", staffIds=" + staffIds +
                ", locationId=" + locationId +
                ", categoryId=" + categoryId +
                '}';
    }

    public String getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(String afterDate) {
        this.afterDate = afterDate;
    }

    public String getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(String beforeDate) {
        this.beforeDate = beforeDate;
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

    public Double getPriceLessThan() {
        return priceLessThan;
    }

    public void setPriceLessThan(Double priceLessThan) {
        this.priceLessThan = priceLessThan;
    }

    public Double getPriceGreaterThan() {
        return priceGreaterThan;
    }

    public void setPriceGreaterThan(Double priceGreaterThan) {
        this.priceGreaterThan = priceGreaterThan;
    }
}

