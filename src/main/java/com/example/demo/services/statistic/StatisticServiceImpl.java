package com.example.demo.services.statistic;

import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.api.statistic.dtos.StatisticsResponse;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService{
    @Override
    public StatisticsResponse getStatistics(StatisticsRequest request) {
        return null;
    }
}
