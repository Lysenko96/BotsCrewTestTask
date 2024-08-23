package com.lysenko.university;

import com.lysenko.university.model.Degree;
import com.lysenko.university.model.Department;
import com.lysenko.university.model.Lector;
import com.lysenko.university.repository.DepartmentRepository;
import com.lysenko.university.repository.LectorRepository;
import com.lysenko.university.service.DepartmentService;
import com.lysenko.university.service.MenuService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
