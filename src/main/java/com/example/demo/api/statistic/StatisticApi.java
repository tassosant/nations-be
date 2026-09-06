package com.example.demo.api.statistic;

import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.api.statistic.dtos.StatisticsResponse;

public interface StatisticApi {
    StatisticsResponse getStatistics(StatisticsRequest request);
}
