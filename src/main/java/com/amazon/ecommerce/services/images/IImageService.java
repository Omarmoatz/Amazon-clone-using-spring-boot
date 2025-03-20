package com.amazon.ecommerce.services.images;

import java.util.List;

import com.amazon.ecommerce.models.Image;

public interface IImageService {
    Image getImageById(Long id);
    String deleteImageById(Long id);

    List<Image> addImage();
    Image updateImage();
}
