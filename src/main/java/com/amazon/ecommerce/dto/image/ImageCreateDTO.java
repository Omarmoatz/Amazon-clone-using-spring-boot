package com.amazon.ecommerce.dto.image;

import lombok.Data;

@Data
public class ImageCreateDTO {
    private Long imageId;

    private String imageName;

    private String downloadUrl;

}
