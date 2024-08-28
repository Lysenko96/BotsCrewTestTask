package com.lysenko.university;

import com.lysenko.university.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityApplication {

    private final MenuService menuService;

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Component
    public class ConsoleRunner implements CommandLineRunner {

        @Override
        public void run(String[] args) {
            menuService.menu();
        }
    }
}
