package edu.miu.cs544.awais.EventManagementService.staff.dto;

import edu.miu.cs544.awais.EventManagementService.staff.StaffRole;

public class StaffDTO {

    private Long id;
    private String username;
    private String email;
    private StaffRole staffRole;

    public StaffDTO() {}

    public StaffDTO(Long id, String username, String email, StaffRole staffRole) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.staffRole = staffRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }
}
