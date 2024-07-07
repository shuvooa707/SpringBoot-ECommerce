package com.ecommerceforpondit.ecommerceforpondit.service;

import com.ecommerceforpondit.ecommerceforpondit.dto.RegisterRequestDto;
import com.ecommerceforpondit.ecommerceforpondit.model.User;
import com.ecommerceforpondit.ecommerceforpondit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Value("${user.images-path}")
    private String productImagesPath;

    public void createUser(RegisterRequestDto registerRequestDto, MultipartFile thumbnailImage) throws IOException {
        User user = new User();
        user.setName(registerRequestDto.getName());
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEmail(registerRequestDto.getEmail());
        user.setThumbnail(saveThumbnailImage(thumbnailImage));
        userRepository.save(user);
    }

    public String saveThumbnailImage(MultipartFile thumbnailImage) throws IOException {
        String extension = StringUtils.getFilenameExtension(thumbnailImage.getOriginalFilename());
        String filename = System.currentTimeMillis() +"."+ extension;
        fileUploadService.uploadFile(thumbnailImage,productImagesPath + "/" + filename);

        return filename;
    }
}
