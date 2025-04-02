package com.amazon.ecommerce.dto.image;

import java.sql.Blob;

import com.amazon.ecommerce.dto.product.RetrieveProductDTO;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ImageRetrieveDTO {
     private Long id;

    private String filName;

    private String filType;

    @Lob
    private Blob image;

    private String downloadUrl;

    private RetrieveProductDTO product;
}
