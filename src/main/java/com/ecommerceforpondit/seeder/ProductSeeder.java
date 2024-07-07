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

import java.util.List;
import java.util.stream.IntStream;

@Component
public class ProductSeeder {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductAttributeRepository productAttributeRepository;

    public void seed() {
        Faker faker = new Faker();
        List<Category> categories = categoryRepository.findAll();

        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setName( faker.name().title() );
            product.setPrice( faker.random().nextDouble() );
            product.setThumbnail("/thumbnails/product/" + i);
            product.setStatus(true);
            product.setCategory(categories.get( (int)(Math.random() * categories.size()) ));


            productRepository.save(product);

            IntStream
              .range(0, (int) (Math.random() * 10))
              .forEach(n -> {
                  ProductAttribute productAttribute = new ProductAttribute();
                  productAttribute.setName(faker.lorem().word() + n);
                  productAttribute.setValue(faker.company().buzzword() + n);
                  productAttribute.setProduct(product);
                  productAttributeRepository.save(productAttribute);
              });
        }
    }
}
