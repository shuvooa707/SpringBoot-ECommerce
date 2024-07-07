package com.ecommerceforpondit.ecommerceforpondit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "wishlist")
@Data
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToMany
    private List<Product> products;


    @OneToOne
    private User user;
}
