package com.lysenko.university.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String head;
    @ManyToMany(mappedBy = "departmentList")
    private List<Lector> lectorList;

    public Department(String name, String head) {
        this.name = name;
        this.head = head;
    }

}
