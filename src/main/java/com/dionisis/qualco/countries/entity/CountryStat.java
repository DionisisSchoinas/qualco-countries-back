package com.dionisis.qualco.countries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "country_stats", schema = "nation")
public class CountryStat {
    @EmbeddedId
    private CountryStatId id;

    @MapsId("countryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "population")
    private Integer population;

    @Column(name = "gdp", precision = 15)
    private BigDecimal gdp;

}