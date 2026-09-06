package com.example.demo.api.country;

import com.example.demo.api.country.dtos.CountriesResponse;
import com.example.demo.api.country.dtos.CountryLanguagesResponse;
import com.example.demo.api.country.dtos.GdpDatasResponse;

public interface CountryApi {

    CountriesResponse getAllCountries();

    CountryLanguagesResponse getCountryLanguages(Integer countryId);

    GdpDatasResponse getGdpData();
}
