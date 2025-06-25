package com.example.majorapp.repository;

import com.example.majorapp.entity.Bookmark;
import com.example.majorapp.entity.BookmarkId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    List<Bookmark> findByUserId(Long userId);
}
