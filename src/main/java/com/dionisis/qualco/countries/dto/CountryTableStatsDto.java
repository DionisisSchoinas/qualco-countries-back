package com.dionisis.qualco.countries.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CountryTableStatsDto {
    String continentName;
    String regionName;
    String countryName;
    Integer year;
    Integer population;
    BigDecimal gdp;
}
