package com.amazon.ecommerce.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private String brand;

    private BigDecimal price;

    private String description;

    private int quantity;   

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, 
                        CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn( name= "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Product(String name, String brand, BigDecimal price, String description, int quantity, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
    }

}
