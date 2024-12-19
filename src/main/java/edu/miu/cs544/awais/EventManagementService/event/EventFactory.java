package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.category.CategoryRepository;
import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.EventFilterDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.location.LocationRepository;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.shared.EventSpecification;
import edu.miu.cs544.awais.EventManagementService.staff.StaffRepository;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventFactory {
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final StaffRepository staffRepository;

    public EventFactory(CategoryRepository categoryRepository, LocationRepository locationRepository,
                        StaffRepository staffRepository) {
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.staffRepository = staffRepository;
    }


    public Event createEvent(CreateEventDTO request) {
        Category category = getCategoryById(request.getCategoryId());
        Location location = getLocationById(request.getLocationId());
        List<Staff> staff = getStaffByIds(request.getStaffIds());
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
            Location location = getLocationById(request.getLocationId());
            event.setLocation(location);
        }
        if (request.getCategoryId() != null) {
            Category category = getCategoryById(request.getCategoryId());
            event.setCategory(category);
        }
        if (request.getStaffIds() != null && !request.getStaffIds().isEmpty()) {
            event.getStaff().clear();
            List<Staff> staff = getStaffByIds(request.getStaffIds());
            event.setStaff(staff);
        }
    }

    public Specification<Event> generateEventSpecification(EventFilterDTO request) {
        Specification<Event> specification = Specification.where(null);
        if (request.getBeforeDate() != null) {
            specification = specification.and(EventSpecification.beforeDatePredicate(request.getBeforeDate()));
        }
        if (request.getAfterDate() != null) {
            specification = specification.and(EventSpecification.afterDatePredicate(request.getAfterDate()));
        }
        if (request.getName() != null && !request.getName().isEmpty()) {
            specification = specification.and(EventSpecification.nameLikePredicate(request.getName()));
        }
        if (request.getLocationId() != null) {
            Location location = getLocationById(request.getLocationId());
            specification = specification.and(EventSpecification.locationPredicate(location.getId()));
        }
        if (request.getCategoryId() != null) {
            Category category = getCategoryById(request.getCategoryId());
            specification = specification.and(EventSpecification.categoryPredicate(category.getId()));
        }

        if (request.getStaffIds() != null && !request.getStaffIds().isEmpty()) {
            List<Staff> staffMembers = getStaffByIds(request.getStaffIds());
            specification = specification.and(EventSpecification.staffInPredicate(request.getStaffIds()));
        }
        return specification;
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException(
                "Category not found for id: " + categoryId));
    }

    private Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new EntityNotFoundException(
                "Location not found for id: " + locationId));
    }

    private List<Staff> getStaffByIds(List<Long> emIds) {
        List<Staff> staffList = staffRepository.findAllById(emIds);
        List<Long> missingIds = emIds.stream()
                .filter(id -> staffList.stream().noneMatch(staff -> staff.getId().equals(id)))
                .toList();
        if (!missingIds.isEmpty()) {
            throw new EntityNotFoundException("Staff not found for IDs: " + missingIds);
        }
        return staffList;
    }
}
