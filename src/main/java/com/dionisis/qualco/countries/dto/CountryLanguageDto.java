package com.dionisis.qualco.countries.dto;

import lombok.Value;

@Value
public class CountryLanguageDto {
    CountryDto country;
    LanguageDto language;
    Boolean official;
}