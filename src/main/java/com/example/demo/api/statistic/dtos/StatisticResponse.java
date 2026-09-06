package com.example.demo.api.statistic.dtos;

import lombok.Builder;

@Builder
public record StatisticResponse(
        Integer id,
        String continentName,
        String regionName,
        String countryName,
        String population,
        Integer year,
        String gdp
) {
}
