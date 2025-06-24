package com.example.majorapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {
    private final Path root = Paths.get("uploads");

    public FileStorageService() throws IOException {
        Files.createDirectories(root);
    }

    public List<String> storeAll(List<MultipartFile> files) throws IOException {
        List<String> urls = new ArrayList<>();
        if (files == null) return urls;
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String filename = System.currentTimeMillis() + "_" + Path.of(file.getOriginalFilename()).getFileName();
            Path dest = root.resolve(filename);
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
            urls.add("/uploads/" + filename);
        }
        return urls;
    }
}