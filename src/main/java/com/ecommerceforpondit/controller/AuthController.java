package com.ecommerceforpondit.ecommerceforpondit.controller;


import com.ecommerceforpondit.ecommerceforpondit.dto.LoginRequestDto;
import com.ecommerceforpondit.ecommerceforpondit.dto.ProfileDTO;
import com.ecommerceforpondit.ecommerceforpondit.dto.RegisterRequestDto;
import com.ecommerceforpondit.ecommerceforpondit.model.User;
import com.ecommerceforpondit.ecommerceforpondit.repository.UserRepository;
import com.ecommerceforpondit.ecommerceforpondit.service.CookieService;
import com.ecommerceforpondit.ecommerceforpondit.service.CustomUserDetailsService;
import com.ecommerceforpondit.ecommerceforpondit.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    UserService userService;
    @Autowired
    CookieService cookieService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping({"/login", "login"})
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) throws Exception {
//        System.out.println(loginRequestDto.toString());
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//        System.out.println(authentication.isAuthenticated());
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> userOptional = userRepository.findByUsername(loginRequestDto.getUsername());

        String JSESSIONID = cookieService.createCookie(loginRequestDto.getUsername());
        Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(cookie);


        return ResponseEntity.ok(new Object() {
            public final String message = "success";
            public final User user = userOptional.get();
//            private final ProfileDTO user = profileDto;
        });
    }

    @GetMapping({"/login", "login"})
    public ResponseEntity<?> loging(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        return ResponseEntity.ok(new Object() {
            private final String message = "success";
//            private final ProfileDTO user = profileDto;
        });
    }

    @PostMapping({"/register", "register"})
    public ResponseEntity<?> register(@RequestPart("thumbnail") MultipartFile thumbnail, @RequestPart("data")  String data) throws IOException {
        RegisterRequestDto registerRequestDto = convertToRegisterRequestDto(data);
        System.out.println(registerRequestDto.toString());
        userService.createUser(registerRequestDto, thumbnail);

        return ResponseEntity.ok(new Object() {
            public final String message = "success";
//            private final ProfileDTO user = profileDto;
        });
    }

    private RegisterRequestDto convertToRegisterRequestDto(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(data, RegisterRequestDto.class);
    }
}
