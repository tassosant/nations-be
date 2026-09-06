package com.example.demo.services.region;

import com.example.demo.api.region.dtos.RegionResponse;
import com.example.demo.api.region.dtos.RegionsResponse;
import com.example.demo.datasource.entities.RegionEntity;
import com.example.demo.datasource.repositories.RegionRepository;
import com.example.demo.mappers.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public RegionsResponse getAllRegions() {
        List<RegionEntity> regionEntities = regionRepository.findAll();
        List<RegionResponse> regions = regionEntities.stream().map(regionMapper::toRegionResponse).toList();

        return new RegionsResponse(regions);
    }
}
