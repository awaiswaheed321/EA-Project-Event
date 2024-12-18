package edu.miu.cs544.awais.EventManagementService.staff;

import edu.miu.cs544.awais.EventManagementService.staff.dto.CreateStaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.StaffDTO;
import edu.miu.cs544.awais.EventManagementService.staff.dto.UpdateStaffDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StaffDTO> createStaff(@RequestBody @Valid CreateStaffDTO request) {
        return staffService.createStaff(request);
    }

    @GetMapping("/{emId}")
    public ResponseEntity<StaffDTO> getStaff(@PathVariable Long emId) {
        return staffService.getStaffById(emId);
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        return staffService.getAllStaff();
    }

    @PutMapping("/{emId}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable Long emId, @RequestBody UpdateStaffDTO request) {
        return staffService.updateStaff(emId, request);
    }

    @DeleteMapping("/{emId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long emId) {
        staffService.deleteStaff(emId);
        return ResponseEntity.noContent().build();
    }
}