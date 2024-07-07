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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
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
    @GetMapping(value = {"/{id}", "{id}"})
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        Optional<Product> productOptional = productService.findProductById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.ok(new Object() {
                final public String message = "failed";
                final public String error = "Product not found";
            });
        }
        return ResponseEntity.ok(new Object() {
            final public String message = "success";
            final public Product product = productOptional.get();
        });
    }


    @PostMapping({"create/", "create"})
    public ResponseEntity<?> create(@RequestPart("thumbnail") MultipartFile thumbnail, @RequestPart("data") String data) throws IOException {
        ProductDto productDto = convertToProductDto(data);

        System.out.println(productDto.toString());

        try {
            productService.createProduct(productDto, thumbnail);
        } catch (Exception e) {
            return ResponseEntity.ok(new Object() {
                final public String message = "failed";
                final public String error = e.getMessage();
                final public String trace = Arrays.toString(e.getStackTrace());
            });
        }

        return ResponseEntity.ok(new Object() {
            final public String message = "success";
        });
    }

    @PostMapping({"remove/{id}/", "remove/{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) throws IOException {

        HashMap<Integer, Integer> map = new HashMap<>();

        try {
            productService.removeProduct(id);
        } catch (Exception e) {
            return ResponseEntity.ok(new Object() {
                final public String message = "failed";
                final public String error = e.getMessage();
                final public String trace = Arrays.toString(e.getStackTrace());
            });
        }

        return ResponseEntity.ok(new Object() {
            final public String message = "success";
        });
    }

    //
    private ProductDto convertToProductDto(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = objectMapper.readValue(data, ProductDto.class);


        return productDto;
    }
}
