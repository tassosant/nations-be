package com.example.demo.datasource.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CountryStatisticsId implements Serializable {

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountryStatisticsId that)) {
            return false;
        }
        return Objects.equals(countryId, that.countryId)
                && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, year);
    }
}
