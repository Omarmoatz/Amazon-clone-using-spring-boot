package com.amazon.ecommerce.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazon.ecommerce.responses.ApiResponse;
import com.amazon.ecommerce.services.images.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> addImage(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            var images = imageService.addImage(files, productId);
            return ResponseEntity.ok(new ApiResponse("images uplaoded successfully", images));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("failed to upload the images", files));
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        var img = imageService.getImageById(imageId);
        var resource = new ByteArrayResource(img.getImage().getBytes(1, (int) img.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(img.getFilType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + img.getFilName() + "\"")
                .body(resource);

    }

}
