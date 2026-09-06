package com.example.demo.services.statistic.dtos;

import java.math.BigDecimal;

public record StatisticsProjection(
        Integer countryId,
        String continentName,
        String regionName,
        String countryName,
        Integer year,
        Integer population,
        BigDecimal gdp
) {
}
