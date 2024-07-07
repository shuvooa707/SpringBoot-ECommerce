package com.ecommerceforpondit.ecommerceforpondit.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ProductVariantDto {
    public String sku;
    public String variationName;
    public Double price;
    
    public List<ProductVariantOptionDto> options;
}
