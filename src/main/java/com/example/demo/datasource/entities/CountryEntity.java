package com.example.demo.datasource.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "countries")
@RequiredArgsConstructor
public class CountryEntity {

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<CountryLanguageEntity> languages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<CountryStatisticsEntity> statistics;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "area", precision = 10, scale = 2, nullable = false)
    private BigDecimal area;

    @Column(name = "national_day")
    private LocalDate nationalDay;

    @Column(name = "country_code2", length = 2, nullable = false, unique = true)
    private String countryCode2;

    @Column(name = "country_code3", length = 3, nullable = false, unique = true)
    private String countryCode3;
}
