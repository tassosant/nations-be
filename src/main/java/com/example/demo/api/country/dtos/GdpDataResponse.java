package com.example.demo.api.country.dtos;

import lombok.Builder;

@Builder
public record GdpDataResponse(
        Integer countryId,
        String name,
        String countryCode3,
        Integer year,
        String population,
        String gdp
) {
}
