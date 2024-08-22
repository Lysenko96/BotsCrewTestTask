package com.lysenko.university.repository;

import com.lysenko.university.model.Degree;

public interface StatisticDto {

    Degree getDegree();
    Long getCount();
}
