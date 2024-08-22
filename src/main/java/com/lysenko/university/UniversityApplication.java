package com.lysenko.university;

import com.lysenko.university.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversityApplication {

    private final DepartmentService departmentService;

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Component
    public class ConsoleRunner implements CommandLineRunner {

        @Override
        public void run(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println();
                    System.out.println("1. Who is head of department {department_name}: ");
                    System.out.println("2. Show {department_name} statistics: ");
                    System.out.println("3. User Input: Show the average salary for the department {department_name}: ");
                    System.out.println("4. Show count of employee for {department_name}: ");
                    System.out.println("5. Global search by {template}: ");
                    System.out.println("6. Exit");
                    System.out.println();

                    System.out.print("Enter your choice: ");
                    String input = scanner.nextLine();

                    if (input.equals("6")) {
                        return;
                    }

                    if (input.equals("1")) {
                        System.out.print("Enter department name: ");
                        String departmentName = scanner.nextLine();
                        System.out.println(departmentService.headOfDepartment(departmentName));
                    } else if (input.equals("2")) {
                        System.out.print("Enter department name: ");
                        String departmentName = scanner.nextLine();
                        System.out.println(departmentService.showStatistic(departmentName));
                    } else if (input.equals("3")) {
                        System.out.print("Enter department name: ");
                        String departmentName = scanner.nextLine();
                        System.out.println(departmentService.showAverageSalary(departmentName));
                    } else if (input.equals("4")) {
                        System.out.print("Enter department name: ");
                        String departmentName = scanner.nextLine();
                        System.out.println(departmentService.employeesInDepartment(departmentName));
                    } else if (input.equals("5")) {
                        System.out.print("Enter template: ");
                        String template = scanner.nextLine();
                        System.out.println(departmentService.searchByTemplate(template));
                    }
                }
            }
        }
    }
}
