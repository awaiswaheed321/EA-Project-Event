package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.StaffDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    ResponseEntity<StaffDTO> createStaff(CreateStaffDTO request);

    ResponseEntity<StaffDTO> getStaffById(Long emId);

    ResponseEntity<List<StaffDTO>> getAllStaff();

    ResponseEntity<StaffDTO> updateStaff(Long emId, CreateStaffDTO request);

    void deleteStaff(Long emId);
}
