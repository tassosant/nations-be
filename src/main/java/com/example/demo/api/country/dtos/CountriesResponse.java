package com.example.demo.api.country.dtos;

import java.util.List;

public record CountriesResponse(
        List<CountryResponse> countries
) {
}
