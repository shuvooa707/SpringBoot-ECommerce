package com.ecommerceforpondit.ecommerceforpondit.controller;

import com.ecommerceforpondit.ecommerceforpondit.dto.ProductDto;
import com.ecommerceforpondit.ecommerceforpondit.model.Product;
import com.ecommerceforpondit.ecommerceforpondit.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Object> index(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getProducts(pageable);
        return ResponseEntity.ok(new Object() {
            final public String message = "success";
            final public Page<Product> products = productPage;
        });
    }

    @GetMapping(value = {"/ids", "ids"})
    public ResponseEntity<Object> ids() {

        ArrayList<Integer> idstmp = new ArrayList<Integer>();
        idstmp.add(4);

        return ResponseEntity.ok(new Object() {
            final public String message = "success";
            final public ArrayList<Integer> ids = idstmp;
        });
    }


    @PostMapping({"add/", "add"})
    public ResponseEntity<?> create(@RequestParam("id") Integer id) throws IOException {
        System.out.println(id);
        return ResponseEntity.ok(new Object() {
            final public String message = "success";
        });
    }

    @PostMapping({"remove/{id}/", "remove/{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) throws IOException {


        return ResponseEntity.ok(new Object() {
            final public String message = "success";
        });
    }

    @PostMapping({"clear-list/", "clear-list"})
    public ResponseEntity<?> clearList() throws IOException {


        return ResponseEntity.ok(new Object() {
            final public String message = "success";
        });
    }
}
