package edu.miu.cs544.awais.EventManagementService.location;

import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.location.dto.CreateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.UpdateLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {
    ResponseEntity<Location> createLocation(CreateLocationDTO request);

    ResponseEntity<Location> getLocationById(Long emId);

    ResponseEntity<List<Location>> getAllLocations();

    ResponseEntity<Location> updateLocation(Long emId, UpdateLocationDTO request);

    ResponseEntity<Void> deleteLocation(Long emId);

    ResponseEntity<List<Event>> getEventsByLocationId(Long emId);
}
