package com.example.majorapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "majors")
public class Major {
    @Id
    @Column(name = "major_id")
    private Integer id;

    private String code;
    private String name;
    private String description;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToMany
    @JoinTable(
            name = "course_major_rel",
            joinColumns = @JoinColumn(name = "major_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore  // → /api/majors 에선 제외
    private List<Course> courses;

    // getters/setters...


// getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
