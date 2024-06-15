package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.CountryStatDto;
import com.dionisis.qualco.countries.entity.CountryStat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CountryMapper.class)
public interface CountryStatMapper {
    @Mapping(source = "id.year", target = "year")
    CountryStatDto map(CountryStat source);
}
