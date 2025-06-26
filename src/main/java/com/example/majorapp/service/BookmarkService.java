package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;

import java.util.List;

public interface BookmarkService {
    void addBookmark(String username, Integer courseId);
    void removeBookmark(String username, Integer courseId);
    List<Integer> getBookmarkIds(String username);
    List<CourseDto> getBookmarkedCourses(String username);
}
