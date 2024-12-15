package edu.miu.cs544.awais.EventManagementService.location;

import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.location.dto.CreateLocationDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.LocationDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ResponseEntity<LocationDTO> createLocation(CreateLocationDTO request) {
        Location location = new Location(request.getLocationName(), request.getStreet(), request.getCity(),
                request.getState(), request.getZip());
        Location savedLocation = locationRepository.save(location);
        return ResponseEntity.ok(new LocationDTO(savedLocation));
    }

    @Override
    public ResponseEntity<LocationDTO> getLocationById(Long emId) {
        Location location = locationRepository.findById(emId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        return ResponseEntity.ok(new LocationDTO(location));
    }

    @Override
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationDTO> locationDTOs = locations.stream().map(LocationDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(locationDTOs);
    }

    @Override
    public ResponseEntity<LocationDTO> updateLocation(Long emId, CreateLocationDTO request) {
        Location location = findLocationById(emId);
        location.setLocationName(request.getLocationName());
        location.setStreet(request.getStreet());
        location.setCity(request.getCity());
        location.setState(request.getState());
        location.setZip(request.getZip());
        Location updatedLocation = locationRepository.save(location);
        return ResponseEntity.ok(new LocationDTO(updatedLocation));
    }

    @Override
    public void deleteLocation(Long emId) {
        Location location = findLocationById(emId);
        locationRepository.delete(location);
    }

    private Location findLocationById(Long emId) {
        return locationRepository.findById(emId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
    }
}