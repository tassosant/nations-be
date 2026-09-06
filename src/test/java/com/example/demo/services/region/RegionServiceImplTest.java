package com.example.demo.services.region;

import com.example.demo.api.region.dtos.RegionResponse;
import com.example.demo.api.region.dtos.RegionsResponse;
import com.example.demo.datasource.entities.RegionEntity;
import com.example.demo.datasource.repositories.RegionRepository;
import com.example.demo.mappers.RegionMapper;
import com.example.demo.HelperTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionServiceImpl regionService;

    @BeforeEach
    void setUp() {
        regionService = new RegionServiceImpl(regionRepository, new RegionMapper());
    }

    @Test
    void getAllRegionsReturnsMappedRepositoryResults() {
        RegionEntity southernEurope = HelperTestData.southernEurope();
        RegionEntity westernEurope = HelperTestData.westernEurope();
        RegionResponse southernEuropeResponse = new RegionResponse(1, "Southern Europe");
        RegionResponse westernEuropeResponse = new RegionResponse(2, "Western Europe");

        when(regionRepository.findAll()).thenReturn(List.of(southernEurope, westernEurope));

        RegionsResponse response = regionService.getAllRegions();

        assertEquals(List.of(southernEuropeResponse, westernEuropeResponse), response.regions());
        verify(regionRepository).findAll();
    }
}
