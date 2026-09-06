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
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    private Integer population;

    @Column(precision = 15)
    private BigDecimal gdp;

}
