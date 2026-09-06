package com.example.demo.api.country.dtos;

import java.util.List;

public record GdpDatasResponse(
        List<GdpDataResponse> gdpData
) {
}
