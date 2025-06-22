package com.example.majorapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_bookmarks")
public class Bookmark {
    @EmbeddedId
    private BookmarkId id;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Bookmark() {}
    public Bookmark(BookmarkId id) {
        this.id = id;
    }

    // ← 추가한 부분
    public BookmarkId getId() { return id; }
    public void setId(BookmarkId id) { this.id = id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
