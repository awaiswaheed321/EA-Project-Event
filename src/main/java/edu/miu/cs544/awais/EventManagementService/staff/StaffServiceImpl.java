package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.shared.exceptionhandler.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.StaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.UpdateStaffDTO;
import org.springframework.http.HttpStatus;
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
        checkIfEmailExists(request.getEmail());
        Staff newStaff = new Staff(request.getUsername(), request.getEmail(), request.getPassword(),
                request.getStaffRole());
        Staff savedStaff = staffRepository.save(newStaff);
        return new ResponseEntity<>(new StaffDTO(savedStaff), HttpStatus.CREATED);
    }

    private void checkIfEmailExists(String email) {
        if (staffRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    @Override
    public ResponseEntity<StaffDTO> getStaffById(Long emId) {
        Staff staff = findStaffById(emId);
        return ResponseEntity.ok(new StaffDTO(staff));
    }

    @Override
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        List<StaffDTO> staffDTOS = staffList.stream()
                .map(StaffDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(staffDTOS);
    }

    @Override
    public ResponseEntity<StaffDTO> updateStaff(Long emId, UpdateStaffDTO request) {
        Staff staff = findStaffById(emId);
        if (request.getUsername() != null) {
            staff.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            staff.setPassword(request.getPassword());
        }
        if (request.getStaffRole() != null) {
            staff.setStaffRole(request.getStaffRole());
        }
        Staff updatedStaff = staffRepository.save(staff);
        return ResponseEntity.ok(new StaffDTO(updatedStaff));
    }

    public void deleteStaff(Long emId) {
        Staff staff = findStaffById(emId);
        staffRepository.delete(staff);
    }

    @Override
    public List<Staff> getStaffByIds(List<Long> emIds) {
        List<Staff> staffList = staffRepository.findAllById(emIds);
        List<Long> missingIds = emIds.stream()
                .filter(id -> staffList.stream().noneMatch(staff -> staff.getEmId().equals(id)))
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
