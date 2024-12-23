package edu.miu.cs544.awais.EventManagementService.domain.event;

import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.domain.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.domain.event.dto.EventFilterDTO;
import edu.miu.cs544.awais.EventManagementService.domain.event.dto.UpdateEventDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event")
@SecurityRequirement(name = "bearerAuth")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Event> createEvent(@RequestBody @Valid CreateEventDTO Event) {
        return eventService.createEvent(Event);
    }

    @GetMapping("/{emId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Event> getEvent(@PathVariable Long emId) {
        return eventService.getEventById(emId);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Event>> getAllEvents(EventFilterDTO filterDTO) {
        return eventService.getAllEvents(filterDTO);
    }

    @PutMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable Long emId, @RequestBody UpdateEventDTO request) {
        return eventService.updateEvent(emId, request);
    }

    @DeleteMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long emId) {
        return eventService.deleteEvent(emId);
    }

    @GetMapping("/with-min-seats")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Event>> getEventsWithMinimumSeats(@RequestParam("minSeats") int minSeats) {
        return eventService.findEventsWithMinimumSeats(minSeats);
    }

    @GetMapping("/upcoming")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        return eventService.findUpcomingEvents();
    }
}
