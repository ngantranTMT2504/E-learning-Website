package com.course.controller;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.course.config.CloudinaryConfig;

@RestController
@RequestMapping("/api/lesson")
public class UploadLessonVideoController {

    private final Cloudinary cloudinary;

    @Autowired
    public UploadLessonVideoController(CloudinaryConfig cloudinaryConfig) {
        this.cloudinary = new Cloudinary(cloudinaryConfig.getConfig());
    }

    @PostMapping("/upload-video")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Không có file video nào được gửi lên.");
        }

        try {
            Transformation eagerTransform = new Transformation()
                .width(720)
                .crop("limit");

            Map<String, Object> params = ObjectUtils.asMap(
                "resource_type", "video",
                "folder", "lesson_videos",
                "public_id", "video_" + System.currentTimeMillis(),
                "chunk_size", 6000000,
                "eager", List.of(eagerTransform)
            );

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);

            return ResponseEntity.ok(Map.of(
                "url", uploadResult.get("secure_url"),
                "duration", uploadResult.get("duration"),
                "format", uploadResult.get("format")
            ));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Upload thất bại: " + e.getMessage());
        }
    }
}
