package com.amazon.ecommerce.services.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.ecommerce.exceptions.ProductNotFoundException;
import com.amazon.ecommerce.models.Product;
import com.amazon.ecommerce.reposotiry.ProductReposotiry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private ProductReposotiry productReposotiry;

    // @Autowired
    // public ProductService( ProductReposotiry productReposotiry){
    //     this.productReposotiry = productReposotiry;
    // }

    
    @Override
    public List<Product> getALlProducts() {
        return productReposotiry.findAll();
    }
    
    @Override
    public Product getProductById(Long id) {
        return productReposotiry.findById(id)
        .orElseThrow(()-> new ProductNotFoundException("product not found with id " + id));
    }

    @Override
    public Product addProduct(Product product) {
        // productReposotiry.save(product);
        return null;
    }

    @Override
    public Product updateProduct(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProductById(Long id) {
        productReposotiry.findById(id).
        ifPresentOrElse(productReposotiry::delete, 
        ()-> new ProductNotFoundException("product not found with id " + id));;
    }


    @Override
    public List<Product> getProductsByCategoryName(String category) {
        return productReposotiry.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productReposotiry.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productReposotiry.findByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productReposotiry.findByName(name);
    }

    @Override
    public List<Product> getProductsByNameAndBrand(String brand, String name) {
        return productReposotiry.findByNameAndBrand(brand, name);
    }

    @Override
    public Long CountByBrandAndName(String brand, String name) {
        return productReposotiry.CountByBrandAndName(brand, name);
    }



    
    
}
