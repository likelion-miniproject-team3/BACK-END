package com.example.majorapp.controller;

import com.example.majorapp.dto.BookmarkRequest;
import com.example.majorapp.service.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/bookmarks")
    public ResponseEntity<?> addBookmark(@RequestBody BookmarkRequest req) {
        bookmarkService.addBookmark(req);
        return ResponseEntity.ok().body("Bookmark added");
    }
}
