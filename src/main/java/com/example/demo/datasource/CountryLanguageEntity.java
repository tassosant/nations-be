package com.example.demo.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country_languages")
@RequiredArgsConstructor
public class CountryLanguageEntity {

    @EmbeddedId
    private CountryLanguageId id;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @Column(name = "official", nullable = false)
    private boolean isOfficial;


}
