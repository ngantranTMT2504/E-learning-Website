package com.course.controller;

import com.course.util.CloudinaryUtil;
import com.course.dto.response.UploadImageResponse;
import com.course.exception.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadImageController {

    @Autowired
    private CloudinaryUtil cloudinaryUtil;

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String url = cloudinaryUtil.uploadFile(file);
            return ResponseEntity.ok(new UploadImageResponse(url));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi server: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-image")
    public ResponseEntity<?> deleteImage(@RequestParam("url") String url) {
        try {
            String publicId = cloudinaryUtil.extractPublicId(url);
            if (publicId == null) return ResponseEntity.badRequest().body("Không thể lấy publicId từ URL");

            String result = cloudinaryUtil.deleteFile(publicId);
            return ResponseEntity.ok("Đã xóa ảnh: " + result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi xóa ảnh: " + e.getMessage());
        }
    }
}
