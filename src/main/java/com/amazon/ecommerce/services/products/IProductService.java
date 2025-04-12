package com.amazon.ecommerce.services.products;

import java.util.List;

import com.amazon.ecommerce.dto.product.AddProductRequestDTO;
import com.amazon.ecommerce.dto.product.RetrieveProductDTO;
import com.amazon.ecommerce.dto.product.UpdateProductRequestDTO;
import com.amazon.ecommerce.models.Product;


public interface IProductService{
    
    List<RetrieveProductDTO> getALlProducts();
    Product findProductById(Long id);
    RetrieveProductDTO getProductById(Long id);
    RetrieveProductDTO addProduct(AddProductRequestDTO request);
    RetrieveProductDTO updateProduct(UpdateProductRequestDTO request, long id);
    void deleteProductById(Long id);

    List<RetrieveProductDTO> getFilteredProducts(String categoryName, String brand, String name);
    Long countByBrandAndName(String brand ,String name);

}
