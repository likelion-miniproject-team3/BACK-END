package com.example.majorapp.dto;

public class RegisterStep4Request {
    private String tempId;
    // Long → Integer 로 변경
    private Integer majorId;

    public RegisterStep4Request() {}

    public String getTempId() {
        return tempId;
    }
    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public Integer getMajorId() {
        return majorId;
    }
    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
