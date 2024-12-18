package edu.miu.cs544.awais.EventManagementService.location;

import edu.miu.cs544.awais.EventManagementService.location.dto.CreateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.LocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.UpdateLocationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody @Valid CreateLocationDTO request) {
        return locationService.createLocation(request);
    }

    @GetMapping("/{emId}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Long emId) {
        return locationService.getLocationById(emId);
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{emId}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Long emId, @RequestBody UpdateLocationDTO request) {
        return locationService.updateLocation(emId, request);
    }

    @DeleteMapping("/{emId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long emId) {
        locationService.deleteLocation(emId);
        return ResponseEntity.noContent().build();
    }
}