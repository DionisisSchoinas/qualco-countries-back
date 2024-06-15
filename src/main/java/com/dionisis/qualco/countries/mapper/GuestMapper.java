package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.GuestDto;
import com.dionisis.qualco.countries.entity.Guest;
import org.mapstruct.Mapper;

@Mapper
public interface GuestMapper {
    GuestDto map(Guest source);
}
