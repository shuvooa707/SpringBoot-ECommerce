package com.ecommerceforpondit.ecommerceforpondit;

import com.ecommerceforpondit.ecommerceforpondit.seeder.ProductSeeder;
import com.ecommerceforpondit.ecommerceforpondit.seeder.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

@SpringBootApplication
@EnableTransactionManagement
public class EcommerceForPonditApplication implements CommandLineRunner {

    @Autowired
    private Seeder seeder;

    @Value(value = "${seeder}")
    String seed;


    public static void main(String[] args) {
        SpringApplication.run(EcommerceForPonditApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        if ( seed.equals("on") ) {
            System.out.println("\n\n\n" + seed + "\n\n\n");
            seeder.seed();
        }
    }
}
