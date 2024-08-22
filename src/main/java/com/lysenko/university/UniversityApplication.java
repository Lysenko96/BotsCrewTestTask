package com.lysenko.university;

import com.lysenko.university.model.Degree;
import com.lysenko.university.model.Department;
import com.lysenko.university.model.Lector;
import com.lysenko.university.repository.DepartmentRepository;
import com.lysenko.university.repository.LectorRepository;
import com.lysenko.university.service.DepartmentService;
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

    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Component
    public class ConsoleRunner implements CommandLineRunner {

        @Override
        public void run(String[] args) {
//            Lector l = new Lector("l1", "n1", Degree.PROFESSOR, 2);
//            Lector l2 = new Lector("l2", "n2", Degree.ASSISTANT, 1);
//            Department department = new Department("dep", "head");
//            l.setDepartmentList(List.of(department));
//            l2.setDepartmentList(List.of(department));
//            department.setLectorList(Arrays.asList(l,l2));
//            departmentRepository.save(department);
//            lectorRepository.save(l);
//            lectorRepository.save(l2);

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


}
