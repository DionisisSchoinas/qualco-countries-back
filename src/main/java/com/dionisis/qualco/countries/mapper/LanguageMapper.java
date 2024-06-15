package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.LanguageDto;
import com.dionisis.qualco.countries.entity.Language;
import org.mapstruct.Mapper;

@Mapper
public interface LanguageMapper {
    LanguageDto map(Language source);
}
