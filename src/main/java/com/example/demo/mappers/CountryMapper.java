package com.example.demo.mappers;

import com.example.demo.api.country.dtos.CountryResponse;
import com.example.demo.datasource.entities.CountryEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryResponse toCountryResponse(CountryEntity country) {
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .area(country.getArea())
                .countryCode2(country.getCountryCode2())
                .build();
    }
}
