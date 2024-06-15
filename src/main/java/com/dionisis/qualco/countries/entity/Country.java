package com.dionisis.qualco.countries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "countries", schema = "nation")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "area", nullable = false, precision = 10, scale = 2)
    private BigDecimal area;

    @Column(name = "national_day")
    private LocalDate nationalDay;

    @Column(name = "country_code2", nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String countryCode2;

    @Column(name = "country_code3", nullable = false, length = 3, columnDefinition = "CHAR(3)")
    private String countryCode3;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

}