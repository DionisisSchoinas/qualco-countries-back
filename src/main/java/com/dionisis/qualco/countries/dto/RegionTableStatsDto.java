package com.dionisis.qualco.countries.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RegionTableStatsDto {
    String continentName;
    String regionName;
    String countryName;
    Integer year;
    Integer population;
    BigDecimal gdp;
}
