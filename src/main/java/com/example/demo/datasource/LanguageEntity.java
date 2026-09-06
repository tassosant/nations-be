package com.example.demo.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "languages")
@RequiredArgsConstructor
public class LanguageEntity {

    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
    private List<CountryLanguageEntity> countryLanguages;

    @Column(name = "language", length = 50, nullable = false)
    private String name;
}
