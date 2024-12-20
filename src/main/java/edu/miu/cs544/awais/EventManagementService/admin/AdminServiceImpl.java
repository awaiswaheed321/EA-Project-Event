package edu.miu.cs544.awais.EventManagementService.admin;

import edu.miu.cs544.awais.EventManagementService.admin.domain.Admin;
import edu.miu.cs544.awais.EventManagementService.admin.dto.CreateAdminDTO;
import edu.miu.cs544.awais.EventManagementService.admin.dto.UpdateAdminDTO;
import edu.miu.cs544.awais.EventManagementService.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.exception.custom.InsufficientAdminsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Admin> createAdmin(CreateAdminDTO request) {
        checkIfEmailExists(request.getEmail());
        Admin admin = new Admin(request.getUsername(), request.getEmail(), request.getPassword());
        return new ResponseEntity<>(adminRepository.save(admin), HttpStatus.CREATED);
    }

    private void checkIfEmailExists(String email) {
        if(adminRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    @Override
    public ResponseEntity<Admin> getAdmin(Long emId) {
        return ResponseEntity.ok(findAdminById(emId));
    }

    @Override
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminRepository.findAll());
    }

    @Override
    public ResponseEntity<Admin> updateAdmin(Long emId, UpdateAdminDTO request) {
        Admin admin = findAdminById(emId);
        updateAdminData(admin, request);
        return new ResponseEntity<>(adminRepository.save(admin), HttpStatus.OK);
    }

    private void updateAdminData(Admin admin, UpdateAdminDTO request) {
        if (request.getUsername() != null) {
            admin.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    @Override
    public void deleteAdmin(Long emId) {
        long count = adminRepository.count();
        if (count > 2) {
            Admin admin = findAdminById(emId);
            adminRepository.delete(admin);
        } else {
            throw new InsufficientAdminsException("Cannot delete admin. At least one admin must remain.");
        }
    }

    private Admin findAdminById(Long emId) {
        return adminRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Admin not found: " + emId));
    }
}
