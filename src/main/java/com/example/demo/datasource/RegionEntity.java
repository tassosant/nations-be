package com.example.demo.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "regions")
@RequiredArgsConstructor
public class RegionEntity {

    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @MapsId("continentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    private ContinentEntity continent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private List<CountryEntity> countries;
}
