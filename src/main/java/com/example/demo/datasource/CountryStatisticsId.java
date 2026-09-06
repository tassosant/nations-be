package com.example.demo.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CountryStatisticsId implements Serializable {

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "year")
    private Integer year;
}
