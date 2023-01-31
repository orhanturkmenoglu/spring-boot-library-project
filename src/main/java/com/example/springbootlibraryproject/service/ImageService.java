package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.response.ImageResponseDto;
import com.example.springbootlibraryproject.entity.Image;
import com.example.springbootlibraryproject.mapper.ImageMapper;
import com.example.springbootlibraryproject.repository.ImageRepository;
import com.example.springbootlibraryproject.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public byte[] downloadImage(String fileName) {
        Optional<Image> imageDate = imageRepository.findByName(fileName);
        return ImageUtil.decompressImage(imageDate.get().getImage());
    }

    @Cacheable(value = "image")
    public List<ImageResponseDto> getImagesAll() {
        List<Image> imageList = imageRepository.findAll();
        return imageMapper.mapToImageResponseDtoList(imageList);
    }
}
