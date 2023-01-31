package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.response.ImageResponseDto;
import com.example.springbootlibraryproject.entity.Image;
import com.example.springbootlibraryproject.util.ImageUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public List<ImageResponseDto> mapToImageResponseDtoList(List<Image> imageList) {
        return imageList.stream()
                .map(this::mapToImageResponseDto)
                .collect(Collectors.toList());
    }

    public ImageResponseDto mapToImageResponseDto(Image image) {
        return ImageResponseDto.builder()
                .image(image.getImage())
                .build();
    }

    public Image mapToImage(MultipartFile file) throws IOException {
        return Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtil.compressImage(file.getBytes()))
                .build();
    }
}
