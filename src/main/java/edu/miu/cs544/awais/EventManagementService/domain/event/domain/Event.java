package edu.miu.cs544.awais.EventManagementService.domain.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.cs544.awais.EventManagementService.domain.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.domain.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.domain.staff.domain.Staff;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Event.findUpcoming",
        query = "SELECT e FROM Event e WHERE e.date > CURRENT_TIMESTAMP"
)
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emId;

    @Version
    @JsonIgnore
    private int version;

    private String name;
    private String description;
    private LocalDateTime date;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double ticketPrice;

    @ManyToMany
    private List<Staff> staff = new ArrayList<>();

    @ManyToOne
    private Location location;

    @ManyToOne
    private Category category;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Event() {
    }

    public Event(String name, String description, LocalDateTime date, Integer totalSeats, Integer availableSeats,
                 Double ticketPrice, List<Staff> staff, Location location, Category category) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.staff = staff;
        this.location = location;
        this.category = category;
    }

    public long getId() {
        return emId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + emId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", ticketPrice=" + ticketPrice +
                ", staff=" + staff +
                ", location=" + location +
                ", category=" + category +
                '}';
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

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
