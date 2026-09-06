package com.example.demo.services.statistic;

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
        return country(1, "Greece", "GRC", "GRC", "101590");
    }

    public static CountryEntity italy() {
        return country(2, "Italy", "ITA", "ITA", "301340");
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
                        new StatisticsRequest(List.of(1), 2000, null, 0, 10),
                        200,
                        "Year from or year to should not be null"
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), null, 2000, 0, 10),
                        200,
                        "Year from or year to should not be null"
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), 2005, 2000, 0, 10),
                        202,
                        "Year from should be less than year to"
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), 2000, nextYear, 0, 10),
                        201,
                        "Year should be in the past"
                )
        );
    }

    public static Stream<Arguments> invalidPaginationRequests() {
        return Stream.of(
                Arguments.of(
                        new StatisticsRequest(List.of(1), null, null, -1, 10),
                        203,
                        "Page number should be zero or positive and size should be positive"
                ),
                Arguments.of(
                        new StatisticsRequest(List.of(1), null, null, 0, 0),
                        203,
                        "Page number should be zero or positive and size should be positive"
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
