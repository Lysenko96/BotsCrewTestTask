package com.lysenko.university.model;

import com.lysenko.university.repository.StatisticDtoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto implements StatisticDtoRepository {

    private Degree degree;
    private Long count;
}
