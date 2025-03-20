package com.amazon.ecommerce.services.images;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Image;
import com.amazon.ecommerce.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("image not found with id" + id));
    }

    @Override
    public String deleteImageById(Long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete,
                        () -> new ResourceNotFoundException("image not found with id " + id));
        return "image deleted successfully";
    }

    @Override
    public List<Image> addImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addImage'");
    }

    @Override
    public Image updateImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateImage'");
    }

}
