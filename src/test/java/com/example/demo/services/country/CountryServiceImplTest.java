package com.example.demo.services.country;

import com.example.demo.api.country.dtos.CountriesResponse;
import com.example.demo.api.country.dtos.CountryLanguagesResponse;
import com.example.demo.api.country.dtos.CountryResponse;
import com.example.demo.api.country.dtos.GdpDataResponse;
import com.example.demo.api.country.dtos.GdpDatasResponse;
import com.example.demo.api.error.CountryError;
import com.example.demo.api.error.NotFoundException;
import com.example.demo.datasource.entities.CountryEntity;
import com.example.demo.datasource.repositories.CountryRepository;
import com.example.demo.mappers.CountryMapper;
import com.example.demo.services.country.dtos.CountryMaxGdpDataProjection;
import com.example.demo.services.statistic.HelperTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @BeforeEach
    void setUp() {
        countryService = new CountryServiceImpl(countryRepository, new CountryMapper());
    }

    @Test
    void getAllCountriesReturnsCountriesMappedFromRepositoryResults() {
        CountryEntity greece = HelperTestData.greece();
        CountryEntity italy = HelperTestData.italy();
        CountryResponse greeceResponse = new CountryResponse(1, "Greece", new BigDecimal("131957.00"), "GR");
        CountryResponse italyResponse = new CountryResponse(2, "Italy", new BigDecimal("301340.00"), "IT");

        when(countryRepository.findAllByOrderByAreaDesc()).thenReturn(List.of(italy, greece));

        CountriesResponse response = countryService.getAllCountries();

        assertEquals(List.of(italyResponse, greeceResponse), response.countries());
        verify(countryRepository).findAllByOrderByAreaDesc();
    }

    @Test
    void getCountryLanguagesReturnsLanguagesAndCountryNameWhenCountryExists() {
        CountryEntity country = HelperTestData.greece();
        List<String> languages = List.of("Greek", "Turkish");

        when(countryRepository.findById(10)).thenReturn(Optional.of(country));
        when(countryRepository.getCountryLanguages(10)).thenReturn(languages);

        CountryLanguagesResponse response = countryService.getCountryLanguages(10);

        assertEquals(languages, response.spokenLanguages());
        assertEquals("Greece", response.countryName());
        verify(countryRepository).findById(10);
        verify(countryRepository).getCountryLanguages(10);
    }

    @Test
    void getCountryLanguagesThrowsNotFoundWhenCountryDoesNotExist() {
        when(countryRepository.findById(10)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> countryService.getCountryLanguages(10)
        );

        assertEquals(CountryError.COUNTRY_NOT_FOUND.code(), exception.code());
        assertEquals(CountryError.COUNTRY_NOT_FOUND.message(), exception.message());
        verify(countryRepository).findById(10);
        verify(countryRepository, never()).getCountryLanguages(10);
    }

    @Test
    void getGdpDataReturnsMappedRepositoryProjectionResults() {
        CountryMaxGdpDataProjection projection = new CountryMaxGdpDataProjection(
                1,
                "Greece",
                "GRC",
                2020,
                10698599,
                new BigDecimal("188835201626.00")
        );
        GdpDataResponse gdpDataResponse = new GdpDataResponse(
                1,
                "Greece",
                "GRC",
                2020,
                "10698599",
                "188835201626.00"
        );

        when(countryRepository.findCountriesWithMaxGdpPerPopulation()).thenReturn(List.of(projection));

        GdpDatasResponse response = countryService.getGdpData();

        assertEquals(List.of(gdpDataResponse), response.gdpData());
        verify(countryRepository).findCountriesWithMaxGdpPerPopulation();

    }
}
