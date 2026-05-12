package com.example.storeservice.dto;

public class LoginRequest {

    private String phone;

    public LoginRequest() {
    }

    public LoginRequest(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}