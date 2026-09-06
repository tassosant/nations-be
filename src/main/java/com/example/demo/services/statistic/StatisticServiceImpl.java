package com.example.demo.services.statistic;

import com.example.demo.api.error.InvalidRequestException;
import com.example.demo.api.error.StatisticsError;
import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.api.statistic.dtos.StatisticsResponse;
import com.example.demo.datasource.entities.RegionEntity;
import com.example.demo.datasource.repositories.CountryRepository;
import com.example.demo.datasource.repositories.RegionRepository;
import com.example.demo.mappers.StatisticsMapper;
import com.example.demo.services.statistic.dtos.StatisticsProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final StatisticsMapper statisticsMapper;

    @Override
    public StatisticsResponse getStatistics(StatisticsRequest request) {
        validateRequest(request);
        List<StatisticsProjection> statisticsProjections = findStatistics(request);
        List<StatisticResponse> statisticResponses = statisticsProjections.stream().map(statisticsMapper::toStatisticResponse).toList();
        return new StatisticsResponse(statisticResponses);
    }

    private List<Integer> getRegionIds(StatisticsRequest request) {
        List<Integer> regionIds = new ArrayList<>();
        if (request.regionIds().isEmpty()) {
            regionIds = regionRepository.findAll().stream().map(RegionEntity::getId).toList();
        } else {
            regionIds = request.regionIds();
        }
        return regionIds;
    }

    private List<StatisticsProjection> findStatistics(StatisticsRequest request) {
        List<Integer> regionIds= getRegionIds(request);
        if (request.yearFrom() == null) {
            return countryRepository.findStatistics(regionIds);
        }
        return countryRepository.findStatistics(regionIds, request.yearFrom(), request.yearTo());
    }

    private void validateRequest(StatisticsRequest request) {
        if (request.yearFrom() == null && request.yearTo() == null) {
            return;
        }
        if (request.yearFrom() == null || request.yearTo() == null) {
            throw new InvalidRequestException(StatisticsError.INVALID_FILTERS);
        }
        if (request.yearFrom() > request.yearTo()) {
            throw new InvalidRequestException(StatisticsError.INVALID_YEAR_RANGE);
        }
        if (request.yearFrom() > Year.now().getValue() || request.yearTo() > Year.now().getValue()) {
            throw new InvalidRequestException(StatisticsError.INVALID_YEAR);
        }
    }
}
