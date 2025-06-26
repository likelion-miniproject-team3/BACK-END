package com.example.majorapp.dto;

public class RegisterStep3Request {
    private String tempId;
    private String password;
    private String confirmPassword;

    public RegisterStep3Request() {}
    public String getTempId() { return tempId; }
    public void setTempId(String tempId) { this.tempId = tempId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
