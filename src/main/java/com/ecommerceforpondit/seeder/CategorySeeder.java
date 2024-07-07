package com.ecommerceforpondit.ecommerceforpondit.seeder;

import com.ecommerceforpondit.ecommerceforpondit.model.Category;
import com.ecommerceforpondit.ecommerceforpondit.model.Product;
import com.ecommerceforpondit.ecommerceforpondit.model.ProductAttribute;
import com.ecommerceforpondit.ecommerceforpondit.repository.CategoryRepository;
import com.ecommerceforpondit.ecommerceforpondit.repository.ProductAttributeRepository;
import com.ecommerceforpondit.ecommerceforpondit.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CategorySeeder {
    @Autowired
    private CategoryRepository categoryRepository;

    public void seed() {
        Faker faker = new Faker();
        for (int i = 1; i <= 20; i++) {
            String catName = faker.commerce().department();
            Category category = new Category();
            category.setName(catName);
            category.setStatus(Boolean.TRUE);
            category.setThumbnail(category.getThumbnail() + ".png");

            categoryRepository.save(category);
        }
    }
}
