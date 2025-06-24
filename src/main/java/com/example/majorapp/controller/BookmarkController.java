package com.example.majorapp.controller;

import com.example.majorapp.dto.BookmarkRequest;
import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.service.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/bookmarks")
public class BookmarkController {

    private final BookmarkService svc;

    public BookmarkController(BookmarkService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<Void> add(
            @PathVariable Long userId,
            @RequestBody BookmarkRequest req
    ) {
        svc.addBookmark(userId, req.courseId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Integer>> listIds(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(svc.getBookmarks(userId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long userId,
            @PathVariable Integer courseId
    ) {
        svc.removeBookmark(userId, courseId);
        return ResponseEntity.ok().build();
    }

    // ← 새로 추가하는 풀 과목 정보 반환 엔드포인트
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> listCourses(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(svc.getBookmarkedCourses(userId));
    }
}
