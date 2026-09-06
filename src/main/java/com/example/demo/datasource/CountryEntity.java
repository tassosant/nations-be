package com.example.demo.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "countries")
@RequiredArgsConstructor
public class CountryEntity {

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO:foreign key
    private Long regionId;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal area;

    private LocalDate nationalDay;
    private String countryCode2;
    private String countryCode3;
}
