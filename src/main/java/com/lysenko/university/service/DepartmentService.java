package com.lysenko.university.service;

import com.lysenko.university.model.Department;
import com.lysenko.university.model.Lector;
import com.lysenko.university.model.StatisticDto;
import com.lysenko.university.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final LectorService lectorService;
    private final DatabasePopulator populator;
    private final DataSource dataSource;

    @PostConstruct
    public void init() {
        DatabasePopulatorUtils.execute(populator, dataSource);
    }

    public String headOfDepartment(String departmentName) {
        Department result = departmentRepository.findByName(departmentName);
        String head = "unknown";
        if (result != null) head = result.getHead();

        return String.format("Head of %s department is %s", departmentName, head);
    }

    public String showStatistic(String departmentName) {
        List<StatisticDto> statisticDtoList = new ArrayList<>();

        Department department = departmentRepository.findByName(departmentName);

        if (department != null) {
            departmentRepository.findStatisticDtoById(department.getId()).forEach(s -> statisticDtoList.add(new StatisticDto(s.getDegree(), s.getCount())));
        }

        return getStatisticByDegree(statisticDtoList);
    }

    private String getStatisticByDegree(List<StatisticDto> statisticDtoList) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (StatisticDto statisticDto : statisticDtoList) {
            joiner.add(String.format("%s - %d ", statisticDto.getDegree(), statisticDto.getCount()));
        }

        return joiner.toString();
    }

    public String showAverageSalary(String departmentName) {
        double averageSalary = departmentRepository.findSalaryAverageByDepartmentName(departmentName);
        return String.format("The average salary of %s is %.2f", departmentName, averageSalary);
    }

    public String employeesInDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        long emloyeeCount = 0;
        if (department != null) {
            List<Lector> lectorList = department.getLectorList();
            emloyeeCount = lectorList.size();
        }

        return String.format("Count of employee is %d", emloyeeCount);
    }

    public String searchByTemplate(String template) {
        List<Lector> lectorList = lectorService.findByTemplate(template);
        StringJoiner joiner = new StringJoiner(",");
        lectorList.forEach(l -> joiner.add(l.getFirstName() + " " + l.getLastName()));

        return joiner.toString();
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department addLector(String departmentId, String lectorId) {
        Department department = departmentRepository.findById(Long.parseLong(departmentId)).orElse(null);
        Lector lector = lectorService.findById(Long.parseLong(lectorId));
        if (department != null && lector != null) {
            List<Lector> lectorList = department.getLectorList();
            lectorList.add(lector);
            department.setLectorList(lectorList);
            department = departmentRepository.save(department);
        }
        departmentRepository.insertLectorIdDepartmentId(Long.parseLong(departmentId), Long.parseLong(lectorId));
        return department;
    }

}
