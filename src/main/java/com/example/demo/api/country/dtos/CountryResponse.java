package com.example.demo.api.country.dtos;

import lombok.Builder;

@Builder
public record CountryResponse(
        Integer id,
        String name,
        Integer area,
        String countryCode2
) {
}
