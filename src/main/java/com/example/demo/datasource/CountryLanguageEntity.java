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

    //TODO:foreign key
    private Long countryId;
    //TODO:foreign key
    private Long languageId;

    @Column(name = "official")
    private boolean isOfficial;


}
