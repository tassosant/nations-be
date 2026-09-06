package com.example.demo.api.statistic;

import com.example.demo.api.common.dtos.PageResponse;
import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.api.statistic.dtos.StatisticsRequest;

public interface StatisticApi {
    PageResponse<StatisticResponse> getStatistics(StatisticsRequest request, int page, int size);
}
