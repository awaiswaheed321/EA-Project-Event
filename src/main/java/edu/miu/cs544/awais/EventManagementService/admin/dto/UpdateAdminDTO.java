package edu.miu.cs544.awais.EventManagementService.admin.dto;

public class UpdateAdminDTO {
    private String username;
    private String password;

    public UpdateAdminDTO() {
    }

    public UpdateAdminDTO(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
