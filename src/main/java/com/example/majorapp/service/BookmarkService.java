package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;
import java.util.List;

public interface BookmarkService {
    void addBookmark(Long userId, Integer courseId);
    void removeBookmark(Long userId, Integer courseId);
    List<Integer> getBookmarks(Long userId);
    List<CourseDto> getBookmarkedCourses(Long userId);
}
