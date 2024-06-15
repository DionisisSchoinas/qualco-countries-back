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
public class CountryStatId implements java.io.Serializable {
    private static final long serialVersionUID = -5958821037297029938L;

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "year", nullable = false)
    private Integer year;
}