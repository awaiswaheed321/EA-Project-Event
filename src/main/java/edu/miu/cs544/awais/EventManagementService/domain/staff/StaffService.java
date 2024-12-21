package edu.miu.cs544.awais.EventManagementService.domain.staff;

import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.domain.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.domain.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.domain.staff.dto.UpdateStaffDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    ResponseEntity<Staff> createStaff(CreateStaffDTO request);

    ResponseEntity<Staff> getStaffById(Long emId);

    ResponseEntity<List<Staff>> getAllStaff();

    ResponseEntity<Staff> updateStaff(Long emId, UpdateStaffDTO request);

    ResponseEntity<Void> deleteStaff(Long emId);

    ResponseEntity<List<Event>> getEventsByStaffId(Long emId);
}
