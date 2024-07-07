package com.ecommerceforpondit.ecommerceforpondit.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Category> categories = new ArrayList<>();

    @OneToMany
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();
}
