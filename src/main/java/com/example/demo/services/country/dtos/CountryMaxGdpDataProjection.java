package com.example.demo.services.country.dtos;

import java.math.BigDecimal;

public record CountryMaxGdpDataProjection(
        Integer id,
        String name,
        String country_code3,
        Integer year,
        Long population,
        BigDecimal gdp
) {
}
