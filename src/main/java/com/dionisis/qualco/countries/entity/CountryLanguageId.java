package com.dionisis.qualco.countries.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CountryLanguageId implements java.io.Serializable {
    private static final long serialVersionUID = -3439934185163234643L;

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "language_id", nullable = false)
    private Integer languageId;
}