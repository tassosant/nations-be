package com.example.demo.datasource.entities;

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

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id", nullable = false)
    private ContinentEntity continent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private List<CountryEntity> countries;
}
