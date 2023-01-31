package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.dto.response.ImageResponseDto;
import com.example.springbootlibraryproject.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@CrossOrigin()
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] image = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getImagesAll() {
        List<ImageResponseDto> imageResponseDtoList = imageService.getImagesAll();
        return ResponseEntity.ok().body(imageResponseDtoList);
    }
}
