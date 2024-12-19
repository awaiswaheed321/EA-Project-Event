package edu.miu.cs544.awais.EventManagementService.location;

import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.location.dto.CreateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.UpdateLocationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Location> createLocation(@RequestBody @Valid CreateLocationDTO request) {
        return locationService.createLocation(request);
    }

    @GetMapping("/{emId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Location> getLocation(@PathVariable Long emId) {
        return locationService.getLocationById(emId);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Location>> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Location> updateLocation(@PathVariable Long emId, @RequestBody UpdateLocationDTO request) {
        return locationService.updateLocation(emId, request);
    }

    @DeleteMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long emId) {
        locationService.deleteLocation(emId);
        return ResponseEntity.noContent().build();
    }
}