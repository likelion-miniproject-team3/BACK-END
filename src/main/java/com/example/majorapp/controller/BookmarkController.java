package com.example.majorapp.controller;

import com.example.majorapp.dto.BookmarkRequest;
import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService svc;

    public BookmarkController(BookmarkService svc) {
        this.svc = svc;
    }

    /** 내 수강바구니에 과목 추가 */
    @PostMapping
    public ResponseEntity<Void> addBookmark(
            @RequestBody BookmarkRequest req,
            Authentication auth
    ) {
        String username = auth.getName();
        svc.addBookmark(username, req.courseId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /** 내 수강바구니 과목 ID 리스트 조회 */
    @GetMapping
    public ResponseEntity<List<Integer>> listBookmarkIds(
            Authentication auth
    ) {
        String username = auth.getName();
        return ResponseEntity.ok(svc.getBookmarkIds(username));
    }

    /** 내 수강바구니에서 과목 제거 */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> removeBookmark(
            @PathVariable Integer courseId,
            Authentication auth
    ) {
        String username = auth.getName();
        svc.removeBookmark(username, courseId);
        return ResponseEntity.ok().build();
    }

    /** 내 수강바구니에 담긴 과목 전체 정보 조회 */
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> listBookmarkedCourses(
            Authentication auth
    ) {
        String username = auth.getName();
        return ResponseEntity.ok(svc.getBookmarkedCourses(username));
    }
}
