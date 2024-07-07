package com.ecommerceforpondit.ecommerceforpondit.seeder;

import com.ecommerceforpondit.ecommerceforpondit.model.Product;
import com.ecommerceforpondit.ecommerceforpondit.model.ProductAttribute;
import com.ecommerceforpondit.ecommerceforpondit.model.Tenant;
import com.ecommerceforpondit.ecommerceforpondit.repository.ProductAttributeRepository;
import com.ecommerceforpondit.ecommerceforpondit.repository.ProductRepository;
import com.ecommerceforpondit.ecommerceforpondit.repository.TenantRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class TenantSeeder {
    @Autowired
    private TenantRepository tenantRepository;

    public void seed() {
        Faker faker = new Faker();
        for (int i = 1; i <= 5; i++) {
            Tenant tenant = new Tenant();
            tenantRepository.save(tenant);
        }
    }
}
