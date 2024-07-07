package com.ecommerceforpondit.ecommerceforpondit.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ProductVariantOptionDto {
    public String optionValue;
    public MultipartFile optionImage;
}
