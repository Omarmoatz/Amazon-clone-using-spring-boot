package com.amazon.ecommerce.services.images;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazon.ecommerce.dto.image.ImageCreateDTO;
import com.amazon.ecommerce.dto.image.ImageRetrieveDTO;
import com.amazon.ecommerce.models.Image;

public interface IImageService {
    List<ImageRetrieveDTO> getAllImages();
    Image getImageById(Long id);
    String deleteImageById(Long id);

    List<ImageCreateDTO> addImage(List<MultipartFile> files, Long productId);
    Image updateImage(MultipartFile file, Long imageId);
}
