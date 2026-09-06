package com.example.demo.services.statistic;

import com.example.demo.api.common.dtos.PageResponse;
import com.example.demo.api.error.InvalidRequestException;
import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.datasource.repositories.CountryRepository;
import com.example.demo.datasource.repositories.RegionRepository;
import com.example.demo.mappers.CrossLayersMapper;
import com.example.demo.mappers.StatisticsMapper;
import com.example.demo.services.statistic.dtos.StatisticsProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.example.demo.services.statistic.HelperTestData.expectedStatisticResponse;
import static com.example.demo.services.statistic.HelperTestData.southernEurope;
import static com.example.demo.services.statistic.HelperTestData.statisticsProjection;
import static com.example.demo.services.statistic.HelperTestData.westernEurope;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private RegionRepository regionRepository;

    private StatisticServiceImpl statisticService;

    @BeforeEach
    void setUp() {
        statisticService = new StatisticServiceImpl(
                countryRepository,
                regionRepository,
                new StatisticsMapper(),
                new CrossLayersMapper()
        );
    }

    @Test
    void getStatisticsWithoutYearFilterAndRegionFilterLoadsAllRegionsAndUsesUnfilteredStatistics() {
        StatisticsProjection projection = statisticsProjection();
        StatisticResponse statisticResponse = expectedStatisticResponse();
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<StatisticsProjection> statisticsPage = HelperTestData.statisticsPage(projection, pageRequest, 1);

        when(regionRepository.findAll()).thenReturn(List.of(southernEurope(), westernEurope()));
        when(countryRepository.findStatistics(List.of(1, 2), pageRequest)).thenReturn(statisticsPage);

        PageResponse<StatisticResponse> response = statisticService.getStatistics(
                new StatisticsRequest(List.of(), null, null, 0, 10)
        );

        assertEquals(List.of(statisticResponse), response.content());
        assertEquals(0, response.page());
        assertEquals(10, response.size());
        assertEquals(1, response.totalElements());
        assertEquals(1, response.totalPages());
        verify(regionRepository).findAll();
        verify(countryRepository).findStatistics(List.of(1, 2), pageRequest);
        verify(countryRepository, never()).findStatistics(List.of(1, 2), null, null, pageRequest);
    }

    @Test
    void getStatisticsWithRegionAndYearFiltersUsesFilteredStatisticsWithoutLoadingAllRegions() {
        StatisticsProjection projection = statisticsProjection();
        StatisticResponse statisticResponse = expectedStatisticResponse();
        StatisticsRequest request = new StatisticsRequest(List.of(7, 9), 2000, 2005, 1, 5);
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<StatisticsProjection> statisticsPage = HelperTestData.statisticsPage(projection, pageRequest, 11);

        when(countryRepository.findStatistics(List.of(7, 9), 2000, 2005, pageRequest)).thenReturn(statisticsPage);

        PageResponse<StatisticResponse> response = statisticService.getStatistics(request);

        assertEquals(List.of(statisticResponse), response.content());
        assertEquals(1, response.page());
        assertEquals(5, response.size());
        assertEquals(11, response.totalElements());
        assertEquals(3, response.totalPages());
        verifyNoInteractions(regionRepository);
        verify(countryRepository).findStatistics(List.of(7, 9), 2000, 2005, pageRequest);
        verify(countryRepository, never()).findStatistics(List.of(7, 9), pageRequest);
    }

    @ParameterizedTest
    @MethodSource("com.example.demo.services.statistic.HelperTestData#invalidYearFilterRequests")
    void getStatisticsThrowsInvalidRequestForInvalidYearFilters(
            StatisticsRequest request,
            int expectedCode,
            String expectedMessage
    ) {
        InvalidRequestException exception = assertInvalidRequest(request);

        assertEquals(expectedCode, exception.code());
        assertEquals(expectedMessage, exception.message());
    }

    @ParameterizedTest
    @MethodSource("com.example.demo.services.statistic.HelperTestData#invalidPaginationRequests")
    void getStatisticsThrowsInvalidRequestForInvalidPagination(
            StatisticsRequest request,
            int expectedCode,
            String expectedMessage
    ) {
        InvalidRequestException exception = assertInvalidRequest(request);

        assertEquals(expectedCode, exception.code());
        assertEquals(expectedMessage, exception.message());
    }

    private InvalidRequestException assertInvalidRequest(StatisticsRequest request) {
        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                () -> statisticService.getStatistics(request)
        );
        verifyNoInteractions(countryRepository, regionRepository);
        return exception;
    }
}
