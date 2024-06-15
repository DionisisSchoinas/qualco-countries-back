package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.CountryLanguageDto;
import com.dionisis.qualco.countries.entity.CountryLanguage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CountryMapper.class, LanguageMapper.class})
public interface CountryLanguageMapper {
    CountryLanguageDto map(CountryLanguage source);
}
