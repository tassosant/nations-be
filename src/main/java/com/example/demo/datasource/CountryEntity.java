package com.example.demo.datasource;

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
    private RegionEntity region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<CountryLanguageEntity> languages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<CountryStatisticsEntity> statistics;

    @Column(length = 50)
    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal area;

    private LocalDate nationalDay;

    @Column(length = 2)
    private String countryCode2;

    @Column(length = 3)
    private String countryCode3;
}
