package com.example.demo.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "region_areas")
@RequiredArgsConstructor
public class RegionAreaEntity {
    @Id
    @Column(name = "region_name", length = 100)
    private String name;

    @Column(name = "region_area", precision = 15, scale = 2)
    private BigDecimal area;
}
