package com.example.majorapp.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookmarkId implements Serializable {
    private Long userId;
    private Long courseId;

    public BookmarkId() {}

    public BookmarkId(Long userId, Long courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BookmarkId)) return false;
        BookmarkId that = (BookmarkId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
