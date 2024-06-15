package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.RegionDto;
import com.dionisis.qualco.countries.entity.Region;
import org.mapstruct.Mapper;

@Mapper(uses = ContinentMapper.class)
public interface RegionMapper {
    RegionDto map(Region source);
}
