package com.ecommerceforpondit.ecommerceforpondit.dto;

import com.ecommerceforpondit.ecommerceforpondit.model.ProductVariantOption;
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
public class ProductAttributeDto {
    public String name;
    public String value;
}
