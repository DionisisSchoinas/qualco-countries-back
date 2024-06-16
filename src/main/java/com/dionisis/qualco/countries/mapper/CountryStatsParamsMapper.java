package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.controller.param.RegionStatsParams;
import com.dionisis.qualco.countries.dto.CountryStatsParamsDto;
import org.mapstruct.Mapper;

@Mapper
public interface CountryStatsParamsMapper {
    CountryStatsParamsDto map(RegionStatsParams source);
}
