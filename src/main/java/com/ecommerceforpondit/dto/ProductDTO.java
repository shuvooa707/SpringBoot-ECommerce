package com.ecommerceforpondit.dto;

import com.ecommerceforpondit.model.Category;
import com.ecommerceforpondit.model.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    public Long id;
    public String name;
    public Double price;
    public String thumbnail = null;
    public Boolean status = Boolean.FALSE;

    public Long categoryId;
    public Category category;
    //public List<Wishlist> wishlists = new ArrayList<>();

    public List<ProductAttributeDto> attributes;
}