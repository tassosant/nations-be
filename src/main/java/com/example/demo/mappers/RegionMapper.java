package com.example.demo.mappers;

import com.example.demo.api.region.dtos.RegionResponse;
import com.example.demo.datasource.entities.RegionEntity;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {

    public RegionResponse toRegionResponse(RegionEntity region) {
        return new RegionResponse(region.getId(), region.getName());
    }
}
