package com.example.majorapp.service;

import com.example.majorapp.dto.BookmarkRequest;

public interface BookmarkService {
    void addBookmark(BookmarkRequest request);
    // 필요하면 다른 메서드도 추가
}
