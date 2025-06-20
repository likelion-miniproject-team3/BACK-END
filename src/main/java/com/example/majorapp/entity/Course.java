package com.example.majorapp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private Integer id;

    private String code;
    private String name;
    private String description;
    private Integer year;
    private Integer semester;
    private Integer credit;

    @Column(name = "is_required")
    private Boolean required;

    @ManyToMany(mappedBy = "courses")
    private List<Major> majors;

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    public Integer getCredit() { return credit; }
    public void setCredit(Integer credit) { this.credit = credit; }
    public Boolean getRequired() { return required; }
    public void setRequired(Boolean required) { this.required = required; }
    public List<Major> getMajors() { return majors; }
    public void setMajors(List<Major> majors) { this.majors = majors; }
}
