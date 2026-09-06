package com.example.demo.api.statistic.dtos;

import java.util.List;

public record StatisticsResponse(
        List<StatisticResponse> statistics
) {
}
