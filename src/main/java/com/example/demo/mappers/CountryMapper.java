package com.example.demo.mappers;

import com.example.demo.api.country.dtos.CountryResponse;
import com.example.demo.api.country.dtos.GdpDataResponse;
import com.example.demo.datasource.entities.CountryEntity;
import com.example.demo.services.country.dtos.CountryMaxGdpDataProjection;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryResponse toCountryResponse(CountryEntity country) {
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .area(country.getArea().toPlainString())
                .countryCode2(country.getCountryCode2())
                .build();
    }

    public GdpDataResponse toGdpDataResponse(CountryMaxGdpDataProjection projection){
        return GdpDataResponse.builder()
                .countryId(projection.countryId())
                .name(projection.countryName())
                .countryCode3(projection.countryCode3())
                .year(projection.year())
                .population(projection.population().toString())
                .gdp(projection.gdp().toPlainString())
                .build();
    }
}
