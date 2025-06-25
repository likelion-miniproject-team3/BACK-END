package com.example.majorapp.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;

@Entity
public class Bookmark {

    @EmbeddedId
    private BookmarkId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    public Bookmark() {}

    public Bookmark(User user, Course course) {
        this.user = user;
        this.course = course;
        this.id = new BookmarkId(user.getId(), course.getId());
    }

    public BookmarkId getId() {
        return id;
    }
    public void setId(BookmarkId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
