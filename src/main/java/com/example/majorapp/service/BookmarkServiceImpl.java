package com.example.majorapp.service;

import com.example.majorapp.dto.BookmarkRequest;
import com.example.majorapp.entity.Bookmark;
import com.example.majorapp.entity.Course;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.BookmarkRepository;
import com.example.majorapp.repository.CourseRepository;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository,
                               UserRepository userRepository,
                               CourseRepository courseRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void addBookmark(BookmarkRequest request) {
        Long userId = request.getUserId();
        Long courseId = request.getCourseId();

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Course> courseOpt = courseRepository.findById(courseId.intValue());  // courseId가 Integer 타입 PK라면 변환 필요

        if (userOpt.isEmpty() || courseOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid userId or courseId");
        }

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(userOpt.get());
        bookmark.setCourse(courseOpt.get());

        bookmarkRepository.save(bookmark);
    }
}
