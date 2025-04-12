package com.amazon.ecommerce.specification;

import org.springframework.data.jpa.domain.Specification;

import com.amazon.ecommerce.models.Product;

public class ProductSpecification {

    public static Specification<Product> hasCategory(String category) {
        return (root, query, builder) -> category == null ? null
                : builder.equal(root.get("category").get("name"), category);
    }

    public static Specification<Product> hasBrand(String brand) {
        return (root, query, builder) -> brand == null ? null : builder.equal(root.get("brand"), brand);
    }

    public static Specification<Product> hasName(String name) {
        return (root, query, builder) -> name == null ? null : builder.equal(root.get("name"), name);
    }
}
