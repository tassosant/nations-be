package com.example.demo.api.statistic.dtos;

import java.util.List;

public record StatisticsRequest(
        List<Integer> regionIds,
        Integer yearFrom,
        Integer yearTo
) {
}
