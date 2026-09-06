package com.example.demo.api.statistic;

import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.api.statistic.dtos.StatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticApi statisticApi;

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics(
            @RequestBody StatisticsRequest request
    ){
        return ResponseEntity.ok(statisticApi.getStatistics(request));
    }
}
