package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.ContinentDto;
import com.dionisis.qualco.countries.entity.Continent;
import org.mapstruct.Mapper;

@Mapper
public interface ContinentMapper {
    ContinentDto map(Continent source);
}
