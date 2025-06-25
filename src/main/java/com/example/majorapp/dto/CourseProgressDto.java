package com.example.majorapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseProgressDto {
    @JsonProperty("major_Id")
    private Long majorId;
    private int requiredCredits;
    private int earnedCredits;
    private int remainingCredits;
    private double progressPercent;

    public CourseProgressDto() {}

    public CourseProgressDto(Long majorId,
                             int requiredCredits,
                             int earnedCredits,
                             int remainingCredits,
                             double progressPercent) {
        this.majorId = majorId;
        this.requiredCredits = requiredCredits;
        this.earnedCredits = earnedCredits;
        this.remainingCredits = remainingCredits;
        this.progressPercent = progressPercent;
    }

    public Long getMajorId() { return majorId; }
    public void setMajorId(Long majorId) { this.majorId = majorId; }

    public int getRequiredCredits() { return requiredCredits; }
    public void setRequiredCredits(int requiredCredits) { this.requiredCredits = requiredCredits; }

    public int getEarnedCredits() { return earnedCredits; }
    public void setEarnedCredits(int earnedCredits) { this.earnedCredits = earnedCredits; }

    public int getRemainingCredits() { return remainingCredits; }
    public void setRemainingCredits(int remainingCredits) { this.remainingCredits = remainingCredits; }

    public double getProgressPercent() { return progressPercent; }
    public void setProgressPercent(double progressPercent) { this.progressPercent = progressPercent; }
}
