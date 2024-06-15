package com.dionisis.qualco.countries.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class CountryDto {
    Integer id;
    String name;
    BigDecimal area;
    LocalDate nationalDay;
    String countryCode2;
    String countryCode3;
    RegionDto region;
}