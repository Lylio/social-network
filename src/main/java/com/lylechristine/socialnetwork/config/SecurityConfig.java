package com.lylechristine.socialnetwork.config;

import com.lylechristine.socialnetwork.service.UserService;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http, UserService userService) throws Exception {
   http.userDetailsService(userService)
     .authorizeHttpRequests(auth->auth
       .requestMatchers("/css/**","/js/**","/images/**","/register","/login").permitAll()
       .requestMatchers("/admin/**").hasRole("ADMIN")
       .anyRequest().authenticated())
     .formLogin(form->form.loginPage("/login").defaultSuccessUrl("/feed",true).permitAll())
     .logout(logout->logout.logoutSuccessUrl("/login?logout"));
   return http.build();
 }
}
