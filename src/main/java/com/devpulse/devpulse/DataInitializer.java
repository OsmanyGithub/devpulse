package com.devpulse.devpulse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner runner(AppUserRepository repo, PasswordEncoder encoder) {
        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {
                AppUser user = new AppUser();
                user.setUsername("admin");
                user.setPassword(encoder.encode("admin123"));
                user.setRole(Role.ADMIN);

                repo.save(user);
            }
        };
    }
}