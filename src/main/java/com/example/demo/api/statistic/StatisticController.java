package com.example.demo.api.statistic;

import com.example.demo.api.common.dtos.PageResponse;
import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.api.statistic.dtos.StatisticsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticApi statisticApi;

    @PostMapping
    public ResponseEntity<PageResponse<StatisticResponse>> getStatistics(
            @RequestBody StatisticsRequest request,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(statisticApi.getStatistics(request, page, size));
    }
}
