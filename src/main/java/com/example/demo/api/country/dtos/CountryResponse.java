package com.example.demo.api.country.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CountryResponse(
        Integer id,
        String name,
        String area,
        String countryCode2
) {
}
