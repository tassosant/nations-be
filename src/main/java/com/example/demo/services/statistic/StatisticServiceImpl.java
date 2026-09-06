package com.example.demo.services.statistic;

import com.example.demo.api.common.dtos.PageResponse;
import com.example.demo.api.error.InvalidRequestException;
import com.example.demo.api.error.StatisticsError;
import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.api.statistic.dtos.StatisticsResponse;
import com.example.demo.datasource.entities.RegionEntity;
import com.example.demo.datasource.repositories.CountryRepository;
import com.example.demo.datasource.repositories.RegionRepository;
import com.example.demo.mappers.CrossLayersMapper;
import com.example.demo.mappers.StatisticsMapper;
import com.example.demo.services.statistic.dtos.StatisticsProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final StatisticsMapper statisticsMapper;
    private final CrossLayersMapper crossLayersMapper;

    @Override
    public PageResponse<StatisticResponse> getStatistics(StatisticsRequest request) {
        validateRequest(request);
        Page<StatisticsProjection> statisticsProjections = findStatistics(request, PageRequest.of(request.page(), request.size()));
        return crossLayersMapper.toPageResponse(statisticsProjections, statisticsMapper::toStatisticResponse);
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

    private Page<StatisticsProjection> findStatistics(StatisticsRequest request, Pageable pageable) {
        List<Integer> regionIds = getRegionIds(request);
        if (request.yearFrom() == null) {
            return countryRepository.findStatistics(regionIds, pageable);
        }
        return countryRepository.findStatistics(regionIds, request.yearFrom(), request.yearTo(), pageable);
    }

    private void validateRequest(StatisticsRequest request) {
        validatePagination(request.page(), request.size());
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

    private void validatePagination(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new InvalidRequestException(StatisticsError.INVALID_PAGE_PARAMS);
        }
    }
}
