package com.example.demo.api.country;


import com.example.demo.api.country.dtos.CountriesResponse;
import com.example.demo.api.country.dtos.CountryLanguagesResponse;
import com.example.demo.api.country.dtos.GdpDatasResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryApi countryApi;

    @GetMapping
    public ResponseEntity<CountriesResponse> getCountries(){
        return ResponseEntity.ok(countryApi.getAllCountries());
    }

    @GetMapping(value = "/{countryId}/languages")
    public ResponseEntity<CountryLanguagesResponse> getCountryLanguages(@PathVariable Integer countryId){
        return ResponseEntity.ok(countryApi.getCountryLanguages(countryId));
    }

    @GetMapping(value = "/gdp-data")
    public ResponseEntity<GdpDatasResponse> getGdpData(){
        return ResponseEntity.ok(countryApi.getGdpData());
    }
}
