package com.dionisis.qualco.countries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "country_languages", schema = "nation")
public class CountryLanguage {
    @EmbeddedId
    private CountryLanguageId id;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "official", nullable = false)
    private Boolean official = false;

}