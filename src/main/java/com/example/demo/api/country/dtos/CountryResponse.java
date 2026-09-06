package com.example.demo.api.country.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CountryResponse(
        Integer id,
        String name,
        BigDecimal area,
        String countryCode2
) {
}
