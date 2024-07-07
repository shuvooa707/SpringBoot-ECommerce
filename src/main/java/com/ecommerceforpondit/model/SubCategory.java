package com.ecommerceforpondit.ecommerceforpondit.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "status", nullable = false)
    private Boolean status = Boolean.FALSE;

}
