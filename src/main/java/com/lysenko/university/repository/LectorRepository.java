package com.lysenko.university.repository;

import com.lysenko.university.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query("FROM Lector l WHERE l.firstName ILIKE %:template% OR l.lastName ILIKE %:template%")
    List<Lector> findByTemplate(String template);

    @Query("FROM Lector l JOIN FETCH l.departmentList")
    List<Lector> findAll();
}
