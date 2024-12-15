package edu.miu.cs544.awais.EventManagementService.category;

import edu.miu.cs544.awais.EventManagementService.event.Event;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emId;

    private String name;
    private String description;

    @ManyToMany
    List<Event> events = new ArrayList<>();

    public Category() {
    }

    public Category(String name, String description, List<Event> events) {
        this.name = name;
        this.description = description;
        this.events = events;
    }

    public long getEmId() {
        return emId;
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

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        this.events.add(event);
    }
}
