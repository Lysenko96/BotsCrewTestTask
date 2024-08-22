package com.lysenko.university.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private int salary;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "lectors_departments",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private List<Department> departmentList;

    public Lector(String firstName, String lastName, Degree degree, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
        this.salary = salary;
    }
}
