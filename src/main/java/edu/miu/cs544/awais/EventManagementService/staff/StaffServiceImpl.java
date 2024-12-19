package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.exception.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.UpdateStaffDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public ResponseEntity<Staff> createStaff(CreateStaffDTO request) {
        checkIfEmailExists(request.getEmail());
        Staff newStaff = new Staff(request.getUsername(), request.getEmail(), request.getPassword(),
                request.getStaffRole());
        return new ResponseEntity<>(staffRepository.save(newStaff), HttpStatus.CREATED);
    }

    private void checkIfEmailExists(String email) {
        if (staffRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    @Override
    public ResponseEntity<Staff> getStaffById(Long emId) {
        return ResponseEntity.ok(findStaffById(emId));
    }

    @Override
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffRepository.findAll());
    }

    @Override
    public ResponseEntity<Staff> updateStaff(Long emId, UpdateStaffDTO request) {
        Staff staff = findStaffById(emId);
        updateStaffData(staff, request);
        return ResponseEntity.ok(staffRepository.save(staff));
    }

    private void updateStaffData(Staff staff, UpdateStaffDTO request) {
        if (request.getUsername() != null) {
            staff.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            staff.setPassword(request.getPassword());
        }
        if (request.getStaffRole() != null) {
            staff.setStaffRole(request.getStaffRole());
        }
    }

    public void deleteStaff(Long emId) {
        Staff staff = findStaffById(emId);
        staffRepository.delete(staff);
    }

    @Override
    public List<Staff> getStaffByIds(List<Long> emIds) {
        List<Staff> staffList = staffRepository.findAllById(emIds);
        List<Long> missingIds = emIds.stream()
                .filter(id -> staffList.stream().noneMatch(staff -> staff.getId().equals(id)))
                .toList();
        if (!missingIds.isEmpty()) {
            throw new EntityNotFoundException("Staff not found for IDs: " + missingIds);
        }
        return staffList;
    }

    private Staff findStaffById(Long emId) {
        return staffRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Staff not found for id " + emId));
    }
}
