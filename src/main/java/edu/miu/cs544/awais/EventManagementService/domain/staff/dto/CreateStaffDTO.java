package edu.miu.cs544.awais.EventManagementService.domain.staff.dto;

import edu.miu.cs544.awais.EventManagementService.domain.staff.StaffRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateStaffDTO {
    @NotEmpty(message = "Staff Name must not be empty")
    private String username;
    @NotEmpty(message = "Staff Email must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    private String email;
    @NotEmpty(message = "Staff password must not be empty")
    private String password;
    @NotNull(message = "Staff Role must not be null")
    private StaffRole staffRole;

    public CreateStaffDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }
}
