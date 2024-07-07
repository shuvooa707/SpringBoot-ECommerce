package com.ecommerceforpondit.ecommerceforpondit.repository;

import com.ecommerceforpondit.ecommerceforpondit.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
