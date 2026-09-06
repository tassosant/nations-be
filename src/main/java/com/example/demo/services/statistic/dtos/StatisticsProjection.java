package com.example.demo.services.statistic.dtos;

import java.math.BigDecimal;

public record StatisticsProjection(
        String continentName,
        String regionName,
        String countryName,
        Integer year,
        Integer population,
        BigDecimal gdp
) {
}
