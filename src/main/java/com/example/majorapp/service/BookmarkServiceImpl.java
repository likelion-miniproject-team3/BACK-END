package com.example.majorapp.service;

import com.example.majorapp.entity.Bookmark;
import com.example.majorapp.entity.BookmarkId;
import com.example.majorapp.repository.BookmarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository repo;

    public BookmarkServiceImpl(BookmarkRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addBookmark(Long userId, Integer courseId) {
        BookmarkId id = new BookmarkId(userId, courseId);
        if (!repo.existsById(id)) {
            repo.save(new Bookmark(id));
        }
    }

    @Override
    public void removeBookmark(Long userId, Integer courseId) {
        repo.deleteById(new BookmarkId(userId, courseId));
    }

    @Override
    public List<Integer> getBookmarks(Long userId) {
        return repo.findAll().stream()
                .filter(b -> b.getId().getUserId().equals(userId))
                .map(b -> b.getId().getCourseId())
                .collect(Collectors.toList());
    }
}