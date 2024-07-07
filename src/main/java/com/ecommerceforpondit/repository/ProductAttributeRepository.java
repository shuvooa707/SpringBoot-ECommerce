package com.ecommerceforpondit.ecommerceforpondit.repository;


import com.ecommerceforpondit.ecommerceforpondit.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
}
