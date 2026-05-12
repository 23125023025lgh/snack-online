package com.example.storeservice.dto;

public class LoginResponse {

    private String token;
    private Long userId;
    private String nickname;
    private String phone;

    public LoginResponse() {
    }

    public LoginResponse(String token, Long userId, String nickname, String phone) {
        this.token = token;
        this.userId = userId;
        this.nickname = nickname;
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}