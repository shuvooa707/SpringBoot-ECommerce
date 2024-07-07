package com.ecommerceforpondit.ecommerceforpondit.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Seeder {
    @Autowired
    ProductSeeder productSeeder;
    @Autowired
    CategorySeeder categorySeeder;
    @Autowired
    TenantSeeder tenantSeeder;

    public void seed() {
        tenantSeeder.seed();
        categorySeeder.seed();
        productSeeder.seed();
    }
}
