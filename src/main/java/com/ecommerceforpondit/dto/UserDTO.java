package com.ecommerceforpondit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.javafaker.Address;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @OneToOne
    private Wishlist wishlist;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Cart cart;

    public String getRoles() {
        return "USER";
    }
}
