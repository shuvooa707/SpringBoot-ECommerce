package com.ecommerceforpondit.ecommerceforpondit.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "product_variant_options")
public class ProductVariantOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "optionValue", nullable = false)
    private String optionValue;

    @Column(name = "optionImage", nullable = true)
    private String optionImage;

    @ManyToOne
    private ProductVariant productVariant;
}
