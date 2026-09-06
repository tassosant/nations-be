package com.example.demo.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "continents")
@RequiredArgsConstructor
public class ContinentEntity {

    @Id
    @Column(name = "continent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "continent")
    private List<RegionEntity> regions;

    @Column(name = "name", nullable = false)
    private String name;
}
