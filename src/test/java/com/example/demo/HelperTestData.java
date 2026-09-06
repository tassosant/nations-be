package com.example.demo;

import com.example.demo.api.error.StatisticsError;
import com.example.demo.api.statistic.dtos.StatisticResponse;
import com.example.demo.api.statistic.dtos.StatisticsRequest;
import com.example.demo.datasource.entities.CountryEntity;
import com.example.demo.datasource.entities.RegionEntity;
import com.example.demo.services.statistic.dtos.StatisticsProjection;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Stream;

public final class HelperTestData {

    private HelperTestData() {
    }

    public static RegionEntity southernEurope() {
        return region(1, "Southern Europe");
    }

    public static RegionEntity westernEurope() {
        return region(2, "Western Europe");
    }

    public static CountryEntity greece() {
        return country(1, "Greece", "GR", "GRC", "101590.00");
    }

    public static CountryEntity italy() {
        return country(2, "Italy", "IT", "ITA", "301340.00");
    }

    public static Page<StatisticsProjection> statisticsPage(
            StatisticsProjection projection,
            PageRequest pageRequest,
            long totalElements
    ) {
        return new PageImpl<>(List.of(projection), pageRequest, totalElements);
    }

    public static StatisticsProjection statisticsProjection() {
        return new StatisticsProjection(
                1,
                "Europe",
                "Southern Europe",
                "Greece",
                2020,
                10698599,
                new BigDecimal("188835201626.00")
        );
    }

    public static StatisticResponse expectedStatisticResponse() {
        return new StatisticResponse(
                1,
                "Europe",
                "Southern Europe",
                "Greece",
                "10698599",
                2020,
                "188835201626.00"
        );
    }

    public static Stream<Arguments> invalidYearFilterRequests() {
        int nextYear = Year.now().getValue() + 1;
        return Stream.of(
                Arguments.of(
                        new StatisticsRequest(List.of(1), 2000, null),
                        StatisticsError.INVALID_FILTERS
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), null, 2000),
                        StatisticsError.INVALID_FILTERS
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), 2005, 2000),
                        StatisticsError.INVALID_YEAR_RANGE
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), 2000, nextYear),
                        StatisticsError.INVALID_YEAR
                )
        );
    }

    public static Stream<Arguments> invalidPaginationRequests() {
        return Stream.of(
                Arguments.of(
                        new StatisticsRequest(List.of(1), null, null),
                        -1,
                        10,
                        StatisticsError.INVALID_PAGE_PARAMS
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), null, null),
                        0,
                        0,
                        StatisticsError.INVALID_PAGE_PARAMS
                )
        );
    }

    private static RegionEntity region(Integer id, String name) {
        RegionEntity region = new RegionEntity();
        region.setId(id);
        region.setName(name);
        return region;
    }

    private static CountryEntity country(
            Integer id,
            String name,
            String countryCode2,
            String countryCode3,
            String area
    ) {
        CountryEntity country = new CountryEntity();
        country.setId(id);
        country.setName(name);
        country.setCountryCode2(countryCode2);
        country.setCountryCode3(countryCode3);
        country.setArea(new BigDecimal(area));
        return country;
    }
}
