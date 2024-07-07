package com.ecommerceforpondit.ecommerceforpondit.service;

import com.ecommerceforpondit.ecommerceforpondit.model.User;
import com.ecommerceforpondit.ecommerceforpondit.repository.UserRepository;
import com.github.javafaker.Crypto;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
@Data
@ToString
public class CookieService {
    @Autowired
    SecretKey secretKey;
    @Autowired
    UserRepository userRepository;

    @Value("${app.secret}")
    private String secret;

    private String username;
    private String passowrd;
    private Long lifespan = 60L;


    public String createCookie(String username) throws Exception {
        String cookie = username + "::" + secret;

        String entryptedCoockie = encrtypt(cookie, secretKey);

        return entryptedCoockie;
    }
    public String createCookie() throws Exception {
       return createCookie(this.username);
    }

    public User getUserFromCookie(String cookie) throws Exception {
        String decryptedCookie = decrypt(cookie, secretKey);

        String username = decryptedCookie.split("::")[0];
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new Exception("User not found");
        }
    }


    private String encrtypt(String payload, SecretKey secretKey) throws Exception {
        // Step 1: Choose the encryption algorithm and mode

        String transformation = "AES/ECB/PKCS5Padding";


        // Step 3: Initialize a cipher object with the key and encryption mode
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Step 4: Encrypt the data using the cipher object
        byte[] encryptedBytes = cipher.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

        return encryptedBase64;
    }

    private String decrypt(String encryptedBase64, SecretKey secretKey) throws Exception {
            // Use the same algorithm, transformation, and secret key as in the encryption example

            // Step 1: Choose the same encryption algorithm and mode used during encryption
            String algorithm = "AES";
            String transformation = "AES/ECB/PKCS5Padding";

            // Step 2: Use the same key that was used during encryption
            // In this example, we assume that you have the secret key from the encryption example


            // Step 3: Initialize a cipher object with the key and decryption mode
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Step 4: Decrypt the data using the cipher object
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

            return decryptedText;
    }
}
