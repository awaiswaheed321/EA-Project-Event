package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid CreateEventDTO Event) {
        return eventService.createEvent(Event);
    }

    @GetMapping("/{emId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long emId) {
        return eventService.getEventById(emId);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping("/{emId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long emId, @RequestBody UpdateEventDTO request) {
        return eventService.updateEvent(emId, request);
    }

    @DeleteMapping("/{emId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long emId) {
        eventService.deleteEvent(emId);
        return ResponseEntity.noContent().build();
    }
}
