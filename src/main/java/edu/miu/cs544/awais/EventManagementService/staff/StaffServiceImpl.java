package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.StaffDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public ResponseEntity<StaffDTO> createStaff(CreateStaffDTO request) {
        Staff staff = new Staff(request.getUsername(), request.getEmail(), request.getPassword(),
                request.getStaffRole());
        Staff savedStaff = staffRepository.save(staff);
        return ResponseEntity.ok(new StaffDTO(savedStaff.getId(), savedStaff.getUsername(),
                savedStaff.getEmail(), savedStaff.getStaffRole()));
    }

    @Override
    public ResponseEntity<StaffDTO> getStaffById(Long emId) {
        Staff staff = findStaffById(emId);
        return ResponseEntity.ok(new StaffDTO(staff.getId(), staff.getUsername(),
                staff.getEmail(), staff.getStaffRole()));
    }

    @Override
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        List<StaffDTO> staffDTOS = staffList.stream()
                .map(staff -> new StaffDTO(staff.getId(), staff.getUsername(), staff.getEmail(), staff.getStaffRole()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(staffDTOS);
    }

    @Override
    public ResponseEntity<StaffDTO> updateStaff(Long emId, CreateStaffDTO request) {
        Staff staff = findStaffById(emId);
        staff.setUsername(request.getUsername());
        staff.setEmail(request.getEmail());
        staff.setPassword(request.getPassword());
        staff.setStaffRole(request.getStaffRole());
        Staff updatedStaff = staffRepository.save(staff);
        return ResponseEntity.ok(new StaffDTO(updatedStaff.getId(), updatedStaff.getUsername(),
                updatedStaff.getEmail(), updatedStaff.getStaffRole()));
    }

    public void deleteStaff(Long emId) {
        Staff staff = findStaffById(emId);
        staffRepository.delete(staff);
    }

    private Staff findStaffById(Long emId) {
        return staffRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Staff not found"));
    }
}
