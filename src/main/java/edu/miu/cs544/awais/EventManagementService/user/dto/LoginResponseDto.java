package edu.miu.cs544.awais.EventManagementService.user.dto;


import edu.miu.cs544.awais.EventManagementService.user.domain.User;

public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private User user;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", user=" + user +
                '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
