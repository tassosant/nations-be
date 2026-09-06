package com.example.demo.mappers;

import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.services.statistic.dtos.StatisticsProjection;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {

    public StatisticResponse toStatisticResponse(StatisticsProjection projection) {
        return StatisticResponse.builder()
                .id(projection.countryId())
                .continentName(projection.continentName())
                .regionName(projection.regionName())
                .countryName(projection.countryName())
                .year(projection.year())
                .population(projection.population().toString())
                .gdp(projection.gdp().toString())
                .build();
    }
}
