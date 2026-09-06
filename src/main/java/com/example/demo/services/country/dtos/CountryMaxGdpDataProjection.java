package com.example.demo.services.country.dtos;

import java.math.BigDecimal;

public record CountryMaxGdpDataProjection(
        Integer countryId,
        String countryName,
        String countryCode3,
        Integer year,
        Integer population,
        BigDecimal gdp
) {
}
