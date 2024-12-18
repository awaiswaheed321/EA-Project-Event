package edu.miu.cs544.awais.EventManagementService.event;

import edu.miu.cs544.awais.EventManagementService.event.dto.CreateEventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.EventDTO;
import edu.miu.cs544.awais.EventManagementService.event.dto.UpdateEventDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {
    ResponseEntity<EventDTO> createEvent(@Valid CreateEventDTO eventDTO);

    ResponseEntity<EventDTO> getEventById(Long emId);

    ResponseEntity<List<EventDTO>> getAllEvents();

    ResponseEntity<EventDTO> updateEvent(Long emId, UpdateEventDTO request);

    void deleteEvent(Long emId);
}
