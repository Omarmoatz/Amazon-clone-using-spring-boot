package com.amazon.ecommerce.dto.image;

import com.amazon.ecommerce.dto.product.RetrieveProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRetrieveDTO {
     private Long id;

    private String filName;

    private String filType;

    private String downloadUrl;

    private RetrieveProductDTO product;
}
