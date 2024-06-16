package com.dionisis.qualco.countries.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CountryGdpDto {
    String name;
    String countryCode3;
    Integer year;
    Integer population;
    BigDecimal gdp;
}
