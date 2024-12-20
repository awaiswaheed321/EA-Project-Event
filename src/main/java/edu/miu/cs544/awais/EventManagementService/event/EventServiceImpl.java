package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.EventFilterDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventFactory eventFactory;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventFactory eventFactory) {
        this.eventRepository = eventRepository;
        this.eventFactory = eventFactory;
    }

    @Override
    public ResponseEntity<Event> createEvent(CreateEventDTO request) {
        Event event = eventFactory.createEvent(request);
        return new ResponseEntity<>(saveEvent(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Event> getEventById(Long emId) {
        return new ResponseEntity<>(findEventById(emId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Event>> getAllEvents(EventFilterDTO filterDTO) {
        Specification<Event> specifications = eventFactory.generateEventSpecification(filterDTO);
        return new ResponseEntity<>(searchEvent(specifications), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Event> updateEvent(Long emId, UpdateEventDTO request) {
        Event event = findEventById(emId);
        eventFactory.updateEventData(event, request);
        return new ResponseEntity<>(saveEvent(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEvent(Long emId) {
        Event event = findEventById(emId);
        if(!event.getAvailableSeats().equals(event.getTotalSeats()) ) {
            throw new IllegalArgumentException("Tickets sold already. Can not be deleted.");
        }
        event.getStaff().clear();
        eventRepository.delete(event);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<Event> searchEvent(Specification<Event> specs) {
        return eventRepository.findAll(specs);
    }

    @Override
    public ResponseEntity<List<Event>> findEventsWithMinimumSeats(int minSeats) {
        return ResponseEntity.ok(eventRepository.findEventsWithMinimumSeats(minSeats));
    }

    @Override
    public ResponseEntity<List<Event>> findUpcomingEvents() {
        return ResponseEntity.ok(eventRepository.findUpcomingEvents());
    }

    @Override
    public Long getEventCountByCategory(Category category) {
        return eventRepository.countByCategory(category);
    }

    @Override
    public Long getEventCountByLocation(Location location) {
        return eventRepository.countByLocation(location);
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    private Event findEventById(Long emId) {
        return eventRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Event not found: " + emId));
    }
}
