package com.lysenko.university.repository;

import com.lysenko.university.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("FROM Department d JOIN FETCH d.lectorList WHERE d.name = :name")
    Department findByName(@Param("name") String name);

    @Query("FROM Department d JOIN FETCH d.lectorList")
    List<Department> findAll();

    @Query(value = "SELECT AVG(l.salary) FROM lectors_departments ld join lector l on l.id = ld.lector_id" +
            " join department d on d.id = ld.department_id WHERE d.name=:name", nativeQuery = true)
    double findSalaryAverageByDepartmentName(@Param("name") String name);

    @Query(value = "INSERT INTO lectors_departments(lector_id, department_id) VALUES(:lectorId, :departmentId) RETURNING department_id", nativeQuery = true)
    Long insertLectorIdDepartmentId(@Param("lectorId") Long lectorId, @Param("departmentId") Long departmentId);

    @Query(value = "SELECT l.degree, COUNT(*) FROM lectors_departments ld JOIN lector l ON ld.lector_id = l.id " +
            "JOIN department d ON ld.department_id = d.id WHERE d.id = :id GROUP BY l.degree", nativeQuery = true)
    List<StatisticDtoRepository> findStatisticDtoById(@Param("id") Long id);
}
