package com.dionisis.qualco.countries.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CountryStatDto {
    CountryDto country;
    Integer year;
    Integer population;
    BigDecimal gdp;
}