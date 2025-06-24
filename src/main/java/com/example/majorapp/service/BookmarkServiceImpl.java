package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.entity.Bookmark;
import com.example.majorapp.entity.BookmarkId;
import com.example.majorapp.entity.Course;
import com.example.majorapp.repository.BookmarkRepository;
import com.example.majorapp.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository repo;
    private final CourseRepository courseRepo;

    public BookmarkServiceImpl(BookmarkRepository repo,
                               CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public void addBookmark(Long userId, Integer courseId) {
        repo.save(new Bookmark(new BookmarkId(userId, courseId)));
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

    @Override
    public List<CourseDto> getBookmarkedCourses(Long userId) {
        // 1) 북마크된 courseId 리스트 조회
        List<Integer> courseIds = repo.findAll().stream()
                .filter(b -> b.getId().getUserId().equals(userId))
                .map(b -> b.getId().getCourseId())
                .collect(Collectors.toList());

        // 2) Course 엔티티 일괄 조회
        List<Course> courses = courseRepo.findAllById(courseIds);

        // 3) 엔티티 → DTO 변환
        return courses.stream()
                .map(c -> new CourseDto(
                        c.getId(),
                        c.getCode(),
                        c.getName(),
                        c.getDescription(),
                        c.getYear(),
                        c.getSemester(),
                        c.getCredit(),
                        c.getRequired()       // Boolean 필드의 getter 사용
                ))
                .collect(Collectors.toList());
    }
}
