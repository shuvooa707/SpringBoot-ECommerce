package com.ecommerceforpondit.ecommerceforpondit.config;

import com.ecommerceforpondit.ecommerceforpondit.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    OauthSuccessHandler oauthSuccessHandler;
    @Autowired
    CustomCookieAuthFilter customCookieAuth;
    @Autowired
    AuthFilter authFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {

        http.cors(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/", "/error", "/webjars/**", "/auth/**", "/oauth2/**").permitAll();
            auth.requestMatchers("/login").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/login").permitAll();
            auth.requestMatchers("/register").permitAll();
            auth.requestMatchers("/**").authenticated();
        });

        http.formLogin(auth -> {
            auth
              .loginProcessingUrl("/login")
              .usernameParameter("username")
              .passwordParameter("password")
              .permitAll();
        });

        // Social Login Setup
//        http.oauth2Login(oauth2login->{
//            oauth2login
//              .successHandler(oauthSuccessHandler);
//        });


//        http.addFilterBefore(customCookieAuth, UsernamePasswordAuthenticationFilter.class);
//
//        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);


        http.formLogin(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//        http.authenticationProvider(authenticationProvider());

        return http.build();
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    @Bean
    @Scope(value = "singleton")
    public SecretKey secretKey() throws NoSuchAlgorithmException {
        String algorithm = "AES";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }
}

