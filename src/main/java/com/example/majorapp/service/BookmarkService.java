package com.example.majorapp.service;

import java.util.List;

public interface BookmarkService {
    void addBookmark(Long userId, Integer courseId);
    void removeBookmark(Long userId, Integer courseId);
    List<Integer> getBookmarks(Long userId);
}