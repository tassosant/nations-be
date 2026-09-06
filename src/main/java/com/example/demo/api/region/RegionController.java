package com.example.demo.api.region;

import com.example.demo.api.region.dtos.RegionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionApi regionApi;

    @GetMapping
    public ResponseEntity<RegionsResponse> getAllRegions(){
        return ResponseEntity.ok(regionApi.getAllRegions());
    }
}
