package com.example.demo.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "country_stats")
@RequiredArgsConstructor
public class CountryStatisticsEntity {

    @EmbeddedId
    private CountryStatisticsId id;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Column(name = "population")
    private Integer population;

    @Column(name = "gdp", precision = 15)
    private BigDecimal gdp;

}
