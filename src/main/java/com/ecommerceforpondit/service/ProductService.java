package com.ecommerceforpondit.ecommerceforpondit.service;

import com.ecommerceforpondit.ecommerceforpondit.dto.ProductDto;
import com.ecommerceforpondit.ecommerceforpondit.model.Product;
import com.ecommerceforpondit.ecommerceforpondit.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    FileUploadService fileUploadService;

    @Value("${product.images-path}")
    private String productImagesPath;

    Page<Product> allProducts(Pageable pageable) {
        return null;
    }

    public void createProduct(ProductDto productDto, MultipartFile thumbnail) throws IOException {
        String extension = StringUtils.getFilenameExtension(thumbnail.getOriginalFilename());
        String filename = System.currentTimeMillis() +"."+ extension;
        fileUploadService.uploadFile(thumbnail,productImagesPath + "/" + filename);

        Product newProduct = new Product();
        newProduct.setName(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setCategory(categoryService.findCategoryById(productDto.getCategoryId()));
        newProduct.setStatus(true);
        newProduct.setThumbnail(filename);

        productRepository.save(newProduct);
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
