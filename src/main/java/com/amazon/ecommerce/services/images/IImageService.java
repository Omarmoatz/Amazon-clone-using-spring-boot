package com.amazon.ecommerce.services.images;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazon.ecommerce.dto.ImageDTO;
import com.amazon.ecommerce.models.Image;

public interface IImageService {
    Image getImageById(Long id);
    String deleteImageById(Long id);

    List<ImageDTO> addImage(List<MultipartFile> files, Long productId);
    Image updateImage(MultipartFile file, Long imageId);
}
