package com.amazon.ecommerce.services.images;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazon.ecommerce.dto.image.ImageCreateDTO;
import com.amazon.ecommerce.dto.image.ImageRetrieveDTO;
import com.amazon.ecommerce.exceptions.ResourceNotFoundException;
import com.amazon.ecommerce.models.Image;
import com.amazon.ecommerce.repository.ImageRepository;
import com.amazon.ecommerce.services.products.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public List<ImageRetrieveDTO> getAllImages() {
        return imageRepository.findAll()
                .stream()
                .map((image) -> modelMapper.map(image, ImageRetrieveDTO.class))
                .collect(Collectors.toList());
    }

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
    public List<ImageCreateDTO> addImage(List<MultipartFile> files, Long productId) {
        var product = productService.findProductById(productId);

        List<ImageCreateDTO> savedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                var image = new Image();
                image.setFilName(file.getOriginalFilename());
                image.setFilType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);
                var savedImage = imageRepository.save(image);

                var url = "api/v1/images/image/download/" + savedImage.getId();
                savedImage.setDownloadUrl(url);
                imageRepository.save(savedImage);

                var imageDto = new ImageCreateDTO();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFilName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());

                savedImages.add(imageDto);

            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return savedImages;
    }

    @Override
    public Image updateImage(MultipartFile file, Long imageId) {
        var image = getImageById(imageId);

        try {
            image.setFilName(file.getOriginalFilename());
            image.setFilType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return imageRepository.save(image);
    }

}
