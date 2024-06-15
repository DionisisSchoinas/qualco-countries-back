package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.VipDto;
import com.dionisis.qualco.countries.entity.Vip;
import org.mapstruct.Mapper;

@Mapper
public interface VipMapper {
    VipDto map(Vip source);
}
