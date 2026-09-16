package com.lylechristine.socialnetwork.config;

import com.lylechristine.socialnetwork.model.Role;
import com.lylechristine.socialnetwork.model.User;
import com.lylechristine.socialnetwork.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();

                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(
                        passwordEncoder.encode("ChangeMe123!")
                );
                admin.setBio("Development administrator account.");
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);

                userRepository.save(admin);
            }
        };
    }
}