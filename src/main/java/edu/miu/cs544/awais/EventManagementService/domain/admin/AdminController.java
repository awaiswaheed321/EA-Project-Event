package edu.miu.cs544.awais.EventManagementService.domain.admin;

import edu.miu.cs544.awais.EventManagementService.domain.admin.domain.Admin;
import edu.miu.cs544.awais.EventManagementService.domain.admin.dto.CreateAdminDTO;
import edu.miu.cs544.awais.EventManagementService.domain.admin.dto.UpdateAdminDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Admin> createAdmin(@RequestBody @Valid CreateAdminDTO request) {
        return adminService.createAdmin(request);
    }

    @GetMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long emId) {
        return adminService.getAdmin(emId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long emId, @RequestBody UpdateAdminDTO request) {
        return adminService.updateAdmin(emId, request);
    }

    @DeleteMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long emId) {
        adminService.deleteAdmin(emId);
        return ResponseEntity.noContent().build();
    }
}
