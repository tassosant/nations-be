package com.example.demo.api.country.dtos;

import java.util.List;

public record CountryLanguagesResponse(
        List<String> spokenLanguages,
        String countryName
) {
}
