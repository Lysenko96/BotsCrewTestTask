package com.lysenko.university.repository;

import com.lysenko.university.model.Degree;

public interface StatisticDtoRepository {

    Degree getDegree();

    Long getCount();
}
