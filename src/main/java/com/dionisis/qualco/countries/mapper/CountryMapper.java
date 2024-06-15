package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.CountryDto;
import com.dionisis.qualco.countries.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = RegionMapper.class)
public interface CountryMapper {
    CountryDto map(Country source);
    List<CountryDto> map(List<Country> source);
}
