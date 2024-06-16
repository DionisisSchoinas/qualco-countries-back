package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.controller.param.CountryStatsParams;
import com.dionisis.qualco.countries.dto.CountryStatDto;
import com.dionisis.qualco.countries.dto.CountryStatsParamsDto;
import com.dionisis.qualco.countries.entity.CountryStat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CountryStatsParamsMapper {
    CountryStatsParamsDto map(CountryStatsParams source);
}
