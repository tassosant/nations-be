package com.example.demo.services.country;

import com.example.demo.api.country.dtos.CountriesResponse;
import com.example.demo.api.country.dtos.CountryLanguagesResponse;
import com.example.demo.api.country.dtos.CountryResponse;
import com.example.demo.api.country.dtos.GdpDatasResponse;
import com.example.demo.api.error.CountryError;
import com.example.demo.api.error.NotFoundException;
import com.example.demo.datasource.entities.CountryEntity;
import com.example.demo.datasource.repositories.CountryRepository;
import com.example.demo.mappers.CountryMapper;
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

        return null;
    }

    @Override
    public GdpDatasResponse getGdpData() {
        return null;
    }

    private CountryEntity getCountryById(Integer countryId){
        return countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException(CountryError.COUNTRY_NOT_FOUND));
    }
}
