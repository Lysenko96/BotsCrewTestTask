package com.lysenko.university.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final DepartmentService departmentService;

    public void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String menu = System.lineSeparator() +
                        "1. Who is head of department {department_name}: " + System.lineSeparator() +
                        "2. Show {department_name} statistics: " + System.lineSeparator() +
                        "3. User Input: Show the average salary for the department {department_name}: " + System.lineSeparator() +
                        "4. Show count of employee for {department_name}: " + System.lineSeparator() +
                        "5. Global search by {template}: " + System.lineSeparator() +
                        "6. Exit" + System.lineSeparator();
                System.out.println(menu);

                System.out.print("Enter your choice: ");
                String input = scanner.nextLine();

                if (input.equals("6")) {
                    return;
                }

                String text = "department name: ";

                if (input.equals("1")) {
                    String result = command(text, scanner);
                    System.out.println(departmentService.headOfDepartment(result));
                } else if (input.equals("2")) {
                    String result = command(text, scanner);
                    System.out.println(departmentService.showStatistic(result));
                } else if (input.equals("3")) {
                    String result = command(text, scanner);
                    System.out.println(departmentService.showAverageSalary(result));
                } else if (input.equals("4")) {
                    String result = command(text, scanner);
                    System.out.println(departmentService.employeesInDepartment(result));
                } else if (input.equals("5")) {
                    text = "Enter template: ";
                    String result = command(text, scanner);
                    System.out.println(departmentService.searchByTemplate(result));
                }
            }
        }
    }

    private String command(String text, Scanner scanner) {
        System.out.print(text);
        String input = scanner.nextLine();
        return input;
    }
}
