package com.ecommerceforpondit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "status", nullable = false)
    private Boolean status = Boolean.FALSE;

    @ManyToOne
    @JsonBackReference
    private Tenant tenant;


    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Product> products;
}
