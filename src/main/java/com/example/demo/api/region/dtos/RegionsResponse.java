package com.example.demo.api.region.dtos;

import java.util.List;

public record RegionsResponse(
        List<RegionResponse> regions
) {
}
