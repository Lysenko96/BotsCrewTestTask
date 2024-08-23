package com.lysenko.university.service;

import com.lysenko.university.model.Degree;
import com.lysenko.university.model.Department;
import com.lysenko.university.model.Lector;
import com.lysenko.university.model.StatisticDto;
import com.lysenko.university.repository.DepartmentRepository;
import com.lysenko.university.repository.LectorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static com.lysenko.university.model.Degree.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private List<Department> departmentList;
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;
    private final DatabasePopulator populator;
    private final DataSource dataSource;

    @PostConstruct
    public void init() {
        DatabasePopulatorUtils.execute(populator, dataSource);
        departmentList = departmentRepository.findAll();
        // findByName
//        System.out.println(departmentRepository.findByName("department"));
    }

    public String headOfDepartment(String departmentName) {
        Department result = departmentList.stream().filter(d -> d.getName().equals(departmentName)).findFirst().orElse(null);
        String head = "unknown";
        if (result != null) head = result.getHead();

        return String.format("Head of %s department is %s", departmentName, head);
    }

    public String showStatistic(String departmentName) {
//        Department department = departmentList.stream().filter(d -> d.getName().equals(departmentName)).findFirst().orElse(null);
        Department department = departmentRepository.findByName(departmentName);
        long assistantsCount = 0;
        long associateProfessorsCount = 0;
        long professorsCount = 0;
        if (department != null) {
//            List<Lector> lectorList = department.getLectorList();
//            assistantsCount = lectorList.stream().filter(l -> l.getDegree().equals(ASSISTANT)).count();
//            associateProfessorsCount = lectorList.stream().filter(l -> l.getDegree().equals(ASSOCIATE_PROFESSOR)).count();
//            professorsCount = lectorList.stream().filter(l -> l.getDegree().equals(PROFESSOR)).count();

            List<StatisticDto> statisticDtoList = new ArrayList<>();
            departmentRepository.findStatisticDtoById(department.getId()).forEach(s -> statisticDtoList.add(new StatisticDto(s.getDegree(), s.getCount())));
            assistantsCount = calcCounterLectorByDegree(statisticDtoList, ASSISTANT);
            associateProfessorsCount = calcCounterLectorByDegree(statisticDtoList, ASSOCIATE_PROFESSOR);
            professorsCount = calcCounterLectorByDegree(statisticDtoList, PROFESSOR);
        }

        return String.format("assistans - %d " + System.lineSeparator() +
                        "associate professors - %d " + System.lineSeparator() +
                        "professors - %d " + System.lineSeparator(),
                assistantsCount, associateProfessorsCount, professorsCount);
    }

    private Long calcCounterLectorByDegree(List<StatisticDto> statisticDtoList, Degree degree) {
        StatisticDto statisticDto = statisticDtoList.stream().filter(s -> s.getDegree().equals(degree)).findFirst().orElse(null);
        if (statisticDto == null) return 0L;

        return statisticDto.getCount();
    }

    public String showAverageSalary(String departmentName) {
//        Department department = departmentList.stream().filter(d -> d.getName().equals(departmentName)).findFirst().orElse(null);
        Department department = departmentRepository.findByName(departmentName);
        long averageSalary = 0;
        if (department != null) {
            List<Lector> lectorList = department.getLectorList();
            averageSalary = lectorList.stream().collect(Collectors.averagingInt(Lector::getSalary)).longValue();
        }
        // very big salary ;)
        BigDecimal averageSalaryBd = new BigDecimal(averageSalary);

        return String.format("The average salary of %s is %d", departmentName, averageSalaryBd.longValueExact());
    }

    public String employeesInDepartment(String departmentName) {
//        Department department = departmentList.stream().filter(d -> d.getName().equals(departmentName)).findFirst().orElse(null);
        Department department = departmentRepository.findByName(departmentName);
        long emloyeeCount = 0;
        if (department != null) {
            List<Lector> lectorList = department.getLectorList();
            emloyeeCount = lectorList.size();
        }

        return String.format("Count of employee is %d", emloyeeCount);
    }

    public String searchByTemplate(String template) {
        List<Lector> lectorList = lectorRepository.findByTemplate(template);
        StringJoiner joiner = new StringJoiner(",");
        lectorList.forEach(l -> joiner.add(l.getFirstName() + " " + l.getLastName()));

        return joiner.toString();
    }

}
