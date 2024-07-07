package com.ecommerceforpondit.ecommerceforpondit.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "product_variants")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "variationName", nullable = false, unique = true)
    private String variationName;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    private Product product;


    @OneToMany
    @JsonManagedReference
    private List<ProductVariantOption> productVariantOptions;
}
