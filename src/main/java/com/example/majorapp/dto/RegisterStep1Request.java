package com.example.majorapp.dto;

public class RegisterStep1Request {
    private String name;
    private String email;
    private String nickname;
    private String studentId;

    public RegisterStep1Request() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}
