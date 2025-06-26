package com.example.majorapp.dto;

public class RegisterStep2Request {
    private String tempId;
    private String username;

    public RegisterStep2Request() {}
    public String getTempId() { return tempId; }
    public void setTempId(String tempId) { this.tempId = tempId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
