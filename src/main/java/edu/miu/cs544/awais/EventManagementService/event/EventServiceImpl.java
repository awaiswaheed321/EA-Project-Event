package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.category.CategoryService;
import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.EventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import edu.miu.cs544.awais.EventManagementService.location.LocationService;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.shared.exceptionhandler.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.shared.mapper.EntityToDtoMapper;
import edu.miu.cs544.awais.EventManagementService.staff.StaffService;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final LocationService locationService;
    private final StaffService staffService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, CategoryService categoryService,
                            LocationService locationService, StaffService staffService) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.staffService = staffService;
    }

    @Override
    public ResponseEntity<EventDTO> createEvent(CreateEventDTO request) {
        Event event = createEventEntity(request);
        Event savedEvent = eventRepository.save(event);
        return new ResponseEntity<>(EntityToDtoMapper.mapEventDTO(savedEvent), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDTO> getEventById(Long emId) {
        Event event = findEventById(emId);
        return new ResponseEntity<>(EntityToDtoMapper.mapEventDTO(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = events.stream().map(EntityToDtoMapper::mapEventDTO).toList();
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EventDTO> updateEvent(Long emId, UpdateEventDTO request) {
        Event event = findEventById(emId);
        updateEventData(event, request);
        Event updatedEvent = eventRepository.save(event);
        return new ResponseEntity<>(EntityToDtoMapper.mapEventDTO(updatedEvent), HttpStatus.OK);
    }

    private void updateEventData(Event event, UpdateEventDTO request) {
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


    @Override
    public void deleteEvent(Long emId) {
        Event event = findEventById(emId);
        eventRepository.delete(event);
    }

    private Event findEventById(Long emId) {
        return eventRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Event not found: " + emId));
    }

    private Event createEventEntity(CreateEventDTO request) {
        Category category = categoryService.findCategoryById(request.getCategoryId());
        Location location = locationService.findLocationById(request.getLocationId());
        List<Staff> staff = staffService.getStaffByIds(request.getStaffIds());
        return new Event(request.getName(), request.getDescription(), request.getDate(), request.getTotalSeats(),
                request.getTotalSeats(), staff, location, category);
    }
}
