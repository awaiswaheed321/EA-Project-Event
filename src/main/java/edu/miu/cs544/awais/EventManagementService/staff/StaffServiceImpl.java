package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.event.EventService;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.shared.EventSpecification;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.UpdateStaffDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventService eventService;

    public StaffServiceImpl(StaffRepository staffRepository, PasswordEncoder passwordEncoder,
                            EventService eventService) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventService = eventService;
    }

    @Override
    public ResponseEntity<Staff> createStaff(CreateStaffDTO request) {
        checkIfEmailExists(request.getEmail());
        Staff newStaff = new Staff(request.getUsername(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
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
            staff.setPassword(passwordEncoder.encode(request.getPassword()));
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
    public ResponseEntity<List<Event>> getEventsByStaffId(Long emId) {
        Staff staff = findStaffById(emId);
        List<Event> events = eventService.searchEvent(EventSpecification.staffInPredicate(List.of(staff.getId())));
        return ResponseEntity.ok(events);
    }

    private Staff findStaffById(Long emId) {
        return staffRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Staff not found for id " + emId));
    }
}
