package edu.miu.cs544.awais.EventManagementService.location;

import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.location.dto.CreateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.LocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.UpdateLocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {
    ResponseEntity<LocationDTO> createLocation(CreateLocationDTO request);

    ResponseEntity<LocationDTO> getLocationById(Long emId);

    ResponseEntity<List<LocationDTO>> getAllLocations();

    ResponseEntity<LocationDTO> updateLocation(Long emId, UpdateLocationDTO request);

    void deleteLocation(Long emId);

    Location findLocationById(Long emId);
}
