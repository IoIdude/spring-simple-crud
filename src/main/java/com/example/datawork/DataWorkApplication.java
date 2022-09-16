package com.example.datawork;

import com.example.datawork.entity.Country;
import com.example.datawork.entity.Role;
import com.example.datawork.entity.WorkBook;
import com.example.datawork.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DataWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWorkApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.addRole(new Role(null, "USER"));
            userService.addRole(new Role(null, "ADMIN"));

            userService.addCountry(new Country(null, "Москва"));
            userService.addCountry(new Country(null, "Питер"));

            userService.addWorkBook(new WorkBook(null, "1234567"));
            userService.addWorkBook(new WorkBook(null, "1234568"));
        };
    }

}
