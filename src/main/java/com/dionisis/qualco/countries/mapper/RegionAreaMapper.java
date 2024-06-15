package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.RegionAreaDto;
import com.dionisis.qualco.countries.entity.RegionArea;
import org.mapstruct.Mapper;

@Mapper
public interface RegionAreaMapper {
    RegionAreaDto map(RegionArea source);
}
