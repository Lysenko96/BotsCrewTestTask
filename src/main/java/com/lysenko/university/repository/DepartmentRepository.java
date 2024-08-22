package com.lysenko.university.repository;

import com.lysenko.university.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
    @Query("FROM Department d JOIN FETCH d.lectorList")
    List<Department> findAll();

    // Bad practice =/
    @Query(value = "SELECT l.degree, COUNT(*) FROM lectors_departments ld JOIN lector l ON ld.lector_id = l.id " +
            "JOIN department d ON ld.department_id = d.id WHERE d.id = :id GROUP BY l.degree", nativeQuery = true)
    List<StatisticDto> findStatisticDtoById(@Param("id")Long id);
}
