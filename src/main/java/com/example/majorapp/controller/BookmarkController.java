package com.example.majorapp.controller;

import com.example.majorapp.dto.BookmarkRequest;
import com.example.majorapp.service.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping
    public ResponseEntity<Void> addBookmark(
            @PathVariable Long userId,
            @RequestBody BookmarkRequest req
    ) {
        bookmarkService.addBookmark(userId, req.courseId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Integer>> listBookmarks(@PathVariable Long userId) {
        return ResponseEntity.ok(bookmarkService.getBookmarks(userId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> removeBookmark(
            @PathVariable Long userId,
            @PathVariable Integer courseId
    ) {
        bookmarkService.removeBookmark(userId, courseId);
        return ResponseEntity.ok().build();
    }
}
