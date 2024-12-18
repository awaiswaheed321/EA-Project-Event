package edu.miu.cs544.awais.EventManagementService.shared.mapper;

import edu.miu.cs544.awais.EventManagementService.category.dto.CategoryDTO;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.event.dto.EventDTO;
import edu.miu.cs544.awais.EventManagementService.location.dto.LocationDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.StaffDTO;

import java.util.stream.Collectors;

public class EntityToDtoMapper {
    public static EventDTO mapEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getEmId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDate(event.getDate());
        eventDTO.setTotalSeats(event.getTotalSeats());
        eventDTO.setAvailableSeats(event.getAvailableSeats());
        eventDTO.setCategory(new CategoryDTO(event.getCategory()));
        eventDTO.setLocation(new LocationDTO(event.getLocation()));
        eventDTO.setStaffs(event.getStaff().stream().map(StaffDTO::new).collect(Collectors.toList()));
        return eventDTO;
    }
}
