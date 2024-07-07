package com.ecommerceforpondit.ecommerceforpondit.service;

import com.ecommerceforpondit.ecommerceforpondit.dto.ProductDto;
import com.ecommerceforpondit.ecommerceforpondit.model.Category;
import com.ecommerceforpondit.ecommerceforpondit.model.Product;
import com.ecommerceforpondit.ecommerceforpondit.repository.CategoryRepository;
import com.ecommerceforpondit.ecommerceforpondit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    Page<Product> allProducts(Pageable pageable) {
        return null;
    }

    public void createProduct(ProductDto productDto) {
        Product newProduct = new Product();
        newProduct.setName("Product 1");
        newProduct.setStatus(true);
        newProduct.setThumbnail("/thumbnail/");

        productRepository.save(newProduct);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
