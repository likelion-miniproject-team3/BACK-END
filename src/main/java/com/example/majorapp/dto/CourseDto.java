package com.example.majorapp.dto;

public class CourseDto {
    private Long id;
    private String name;
    private Integer credit;

    public CourseDto(Long id, String name, Integer credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getCredit() { return credit; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCredit(Integer credit) { this.credit = credit; }
}
