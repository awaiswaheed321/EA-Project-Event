package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.EventFilterDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {
    ResponseEntity<Event> createEvent(@Valid CreateEventDTO Event);

    ResponseEntity<Event> getEventById(Long emId);

    ResponseEntity<List<Event>> getAllEvents(EventFilterDTO filter);

    ResponseEntity<Event> updateEvent(Long emId, UpdateEventDTO request);

    void deleteEvent(Long emId);

    List<Event> searchEvent(Specification<Event> specs);

    ResponseEntity<List<Event>> findEventsWithMinimumSeats(int minSeats);

    ResponseEntity<List<Event>> findUpcomingEvents();
}
