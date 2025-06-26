package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.entity.Bookmark;
import com.example.majorapp.entity.BookmarkId;
import com.example.majorapp.entity.Course;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.BookmarkRepository;
import com.example.majorapp.repository.CourseRepository;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository repo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public BookmarkServiceImpl(BookmarkRepository repo,
                               CourseRepository courseRepo,
                               UserRepository userRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addBookmark(String username, Integer courseId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        repo.save(new Bookmark(new BookmarkId(user.getId(), courseId)));
    }

    @Override
    public void removeBookmark(String username, Integer courseId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        repo.deleteById(new BookmarkId(user.getId(), courseId));
    }

    @Override
    public List<Integer> getBookmarkIds(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long uid = user.getId();
        return repo.findAll().stream()
                .map(b -> b.getId())
                .filter(id -> id.getUserId().equals(uid))
                .map(BookmarkId::getCourseId)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> getBookmarkedCourses(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long uid = user.getId();
        List<Integer> courseIds = repo.findAll().stream()
                .map(Bookmark::getId)
                .filter(id -> id.getUserId().equals(uid))
                .map(BookmarkId::getCourseId)
                .collect(Collectors.toList());

        List<Course> courses = courseRepo.findAllById(courseIds);
        return courses.stream()
                .map(c -> new CourseDto(
                        c.getId(),
                        c.getCode(),
                        c.getName(),
                        c.getDescription(),
                        c.getYear(),
                        c.getSemester(),
                        c.getCredit(),
                        c.getRequired()
                ))
                .collect(Collectors.toList());
    }
}
