package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.UpdateStaffDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    ResponseEntity<Staff> createStaff(CreateStaffDTO request);

    ResponseEntity<Staff> getStaffById(Long emId);

    ResponseEntity<List<Staff>> getAllStaff();

    ResponseEntity<Staff> updateStaff(Long emId, UpdateStaffDTO request);

    void deleteStaff(Long emId);

    List<Staff> getStaffByIds(List<Long> emIds);
}
