package edu.miu.cs544.awais.EventManagementService.domain.admin;

import edu.miu.cs544.awais.EventManagementService.domain.admin.domain.Admin;
import edu.miu.cs544.awais.EventManagementService.domain.admin.dto.CreateAdminDTO;
import edu.miu.cs544.awais.EventManagementService.domain.admin.dto.UpdateAdminDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<Admin> createAdmin(@Valid CreateAdminDTO request);

    ResponseEntity<Admin> getAdmin(Long emId);

    ResponseEntity<List<Admin>> getAllAdmins();

    ResponseEntity<Admin> updateAdmin(Long emId, UpdateAdminDTO request);

    void deleteAdmin(Long emId);
}
