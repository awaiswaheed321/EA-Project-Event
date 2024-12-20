package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.UpdateStaffDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Staff> createStaff(@RequestBody @Valid CreateStaffDTO request) {
        return staffService.createStaff(request);
    }

    @GetMapping("/{emId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<Staff> getStaff(@PathVariable Long emId) {
        return staffService.getStaffById(emId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<Staff>> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{emId}/events")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<Event>> getEventsByStaffId(@PathVariable Long emId) {
        return staffService.getEventsByStaffId(emId);
    }

    @PutMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long emId, @RequestBody UpdateStaffDTO request) {
        return staffService.updateStaff(emId, request);
    }

    @DeleteMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long emId) {
        return staffService.deleteStaff(emId);
    }
}