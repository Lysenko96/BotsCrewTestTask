package com.lysenko.university.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto implements com.lysenko.university.repository.StatisticDto {

    private Degree degree;
    private Long count;
}
