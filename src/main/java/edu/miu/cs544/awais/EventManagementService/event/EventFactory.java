package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.category.CategoryService;
import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import edu.miu.cs544.awais.EventManagementService.location.LocationService;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.staff.StaffService;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventFactory {
    private final CategoryService categoryService;
    private final LocationService locationService;
    private final StaffService staffService;

    public EventFactory(CategoryService categoryService, LocationService locationService, StaffService staffService) {
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.staffService = staffService;
    }

    public Event createEvent(CreateEventDTO request) {
        Category category = categoryService.findCategoryById(request.getCategoryId());
        Location location = locationService.findLocationById(request.getLocationId());
        List<Staff> staff = staffService.getStaffByIds(request.getStaffIds());
        return new Event(request.getName(), request.getDescription(), request.getDate(), request.getTotalSeats(),
                request.getTotalSeats(), staff, location, category);
    }

    public void updateEventData(Event event, UpdateEventDTO request) {
        if (request.getName() != null) {
            event.setName(request.getName());
        }
        if (request.getDescription() != null) {
            event.setDescription(request.getDescription());
        }
        if (request.getDate() != null) {
            event.setDate(request.getDate());
        }
        if (request.getTotalSeats() != null) {
            event.setTotalSeats(request.getTotalSeats());
        }
        if (request.getLocationId() != null) {
            Location location = locationService.findLocationById(request.getLocationId());
            event.setLocation(location);
        }
        if (request.getCategoryId() != null) {
            Category category = categoryService.findCategoryById(request.getCategoryId());
            event.setCategory(category);
        }
        if (request.getStaffIds() != null && !request.getStaffIds().isEmpty()) {
            event.getStaff().clear();
            List<Staff> staffMembers = staffService.getStaffByIds(request.getStaffIds());
            event.setStaff(staffMembers);
        }
    }
}
