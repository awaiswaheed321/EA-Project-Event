package edu.miu.cs544.awais.EventManagementService.staff.domain;

import edu.miu.cs544.awais.EventManagementService.event.Event;
import edu.miu.cs544.awais.EventManagementService.shared.domain.User;
import edu.miu.cs544.awais.EventManagementService.shared.enums.UserRole;
import edu.miu.cs544.awais.EventManagementService.staff.StaffRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Staff extends User {

    @Enumerated(EnumType.STRING)
    private StaffRole staffRole;

    @ManyToMany
    private List<Event> events = new ArrayList<>();

    public Staff() {}

    public Staff(String username, String email, String password, StaffRole staffRole) {
        super(username, email, password, UserRole.STAFF);
        this.staffRole = staffRole;
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
