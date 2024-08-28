package com.lysenko.university.service;

import com.lysenko.university.model.Degree;
import com.lysenko.university.model.Department;
import com.lysenko.university.model.Lector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.StringJoiner;

import static com.lysenko.university.model.Degree.*;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String menu = System.lineSeparator() +
                        "1. Who is head of department {department_name}: " + System.lineSeparator() +
                        "2. Show {department_name} statistics: " + System.lineSeparator() +
                        "3. User Input: Show the average salary for the department {department_name}: " + System.lineSeparator() +
                        "4. Show count of employee for {department_name}: " + System.lineSeparator() +
                        "5. Global search by {template}: " + System.lineSeparator() +
                        "6. Add department {head}, {name}: " + System.lineSeparator() +
                        "7. Add lector {firstName}, {lastName}, {degree}, {salary}: " + System.lineSeparator() +
                        "8. Show all departments: " + System.lineSeparator() +
                        "9. Show all lectors: " + System.lineSeparator() +
                        "10. Add lector to department [department_id] [lector_id]: " + System.lineSeparator() +
                        "11. Exit" + System.lineSeparator();
                System.out.println(menu);

                System.out.print("Enter your choice: ");
                String input = scanner.nextLine();

                if (input.equals("11")) {
                    return;
                }

                String text = "department name: ";

                switch (input) {
                    case "1":
                        String result = command(text, scanner);
                        System.out.println(departmentService.headOfDepartment(result));
                        break;
                    case "2":
                        result = command(text, scanner);
                        System.out.println(departmentService.showStatistic(result));
                        break;
                    case "3":
                        result = command(text, scanner);
                        System.out.println(departmentService.showAverageSalary(result));
                        break;
                    case "4":
                        result = command(text, scanner);
                        System.out.println(departmentService.employeesInDepartment(result));
                        break;
                    case "5":
                        text = "Enter template: ";
                        result = command(text, scanner);
                        System.out.println(departmentService.searchByTemplate(result));
                        break;
                    case "6":
                        text = "Enter new department head: ";
                        String head = command(text, scanner);
                        text = "Enter new department name: ";
                        String name = command(text, scanner);
                        Department department = new Department(head, name);
                        Department departmentDB = departmentService.save(department);
                        System.out.println(departmentDB);
                        break;
                    case "7":
                        text = "Enter new lector firstName: ";
                        String firstName = command(text, scanner);
                        text = "Enter new lector lastName: ";
                        String lastName = command(text, scanner);
                        StringJoiner joiner = new StringJoiner(System.lineSeparator());
                        joiner.add("1 - ASSISTANT").add("2 - ASSOCIATE_PROFESSOR").add("3 - PROFESSOR");
                        System.out.println(joiner);
                        text = "Enter new lector degree: ";
                        String command = command(text, scanner);
                        Degree degree = switch (command) {
                            case "1" -> ASSISTANT;
                            case "2" -> ASSOCIATE_PROFESSOR;
                            case "3" -> PROFESSOR;
                            default -> null;
                        };
                        text = "Enter new lector salary: ";
                        String salary = command(text, scanner);
                        Lector lector = new Lector(firstName, lastName, degree, Integer.parseInt(salary));
                        Lector lectorDB = lectorService.save(lector);
                        System.out.println(lectorDB);
                        break;
                    case "8":
                        System.out.println(departmentService.findAll());
                        break;
                    case "9":
                        System.out.println(lectorService.findAll());
                        break;
                    case "10":
                        text = "Enter department id: ";
                        String departmentId = command(text, scanner);
                        text = "Enter lector id: ";
                        String lectorId = command(text, scanner);
                        department = departmentService.addLector(departmentId, lectorId);
                        System.out.println(department);
                        break;
                    default:
                        menu();
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
