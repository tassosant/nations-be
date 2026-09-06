package com.example.demo.services.country;

import com.example.demo.api.country.dtos.*;
import com.example.demo.api.error.CountryError;
import com.example.demo.api.error.NotFoundException;
import com.example.demo.datasource.entities.CountryEntity;
import com.example.demo.datasource.repositories.CountryRepository;
import com.example.demo.mappers.CountryMapper;
import com.example.demo.services.country.dtos.CountryMaxGdpDataProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public CountriesResponse getAllCountries() {
        List<CountryResponse> countries = countryRepository.findAllByOrderByAreaDesc()
                .stream()
                .map(countryMapper::toCountryResponse)
                .toList();

        return new CountriesResponse(countries);
    }

    @Override
    public CountryLanguagesResponse getCountryLanguages(Integer countryId) {
        CountryEntity country = getCountryById(countryId);
        List<String> languages = countryRepository.getCountryLanguages(countryId);
        return new CountryLanguagesResponse(languages, country.getName());
    }

    @Override
    public GdpDatasResponse getGdpData() {
        List<CountryMaxGdpDataProjection> gdpDataProjections = countryRepository.findCountriesWithMaxGdpPerPopulation();
        List<GdpDataResponse> gdpDataResponses = gdpDataProjections.stream().map(countryMapper::toGdpDataResponse).toList();
        return new GdpDatasResponse(gdpDataResponses);
    }

    private CountryEntity getCountryById(Integer countryId){
        return countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException(CountryError.COUNTRY_NOT_FOUND));
    }
}
