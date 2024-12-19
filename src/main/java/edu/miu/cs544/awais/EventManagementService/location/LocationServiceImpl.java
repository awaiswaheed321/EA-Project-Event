package edu.miu.cs544.awais.EventManagementService.location;

import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.location.dto.CreateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.UpdateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ResponseEntity<Location> createLocation(CreateLocationDTO request) {
        Location location = new Location(request.getLocationName(), request.getStreet(), request.getCity(),
                request.getState(), request.getZip());
        return ResponseEntity.ok(locationRepository.save(location));
    }

    @Override
    public ResponseEntity<Location> getLocationById(Long emId) {
        return ResponseEntity.ok(findLocationById(emId));
    }

    @Override
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    @Override
    public ResponseEntity<Location> updateLocation(Long emId, UpdateLocationDTO request) {
        Location location = findLocationById(emId);
        updateLocationData(location, request);
        return ResponseEntity.ok(locationRepository.save(location));
    }

    private void updateLocationData(Location location, UpdateLocationDTO request) {
        if (request.getLocationName() != null) {
            location.setName(request.getLocationName());
        }
        if (request.getStreet() != null) {
            location.setStreet(request.getStreet());
        }
        if (request.getCity() != null) {
            location.setCity(request.getCity());
        }
        if (request.getState() != null) {
            location.setState(request.getState());
        }
        if (request.getZip() != null) {
            location.setZip(request.getZip());
        }
    }

    @Override
    public void deleteLocation(Long emId) {
        Location location = findLocationById(emId);
        locationRepository.delete(location);
    }

    public Location findLocationById(Long emId) {
        return locationRepository.findById(emId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found for id " + emId));
    }
}