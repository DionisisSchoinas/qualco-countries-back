package com.dionisis.qualco.countries.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RegionAreaDto {
    String regionName;
    BigDecimal regionArea;
}