package com.dionisis.qualco.countries.mapper;

import com.dionisis.qualco.countries.dto.LanguageDto;
import com.dionisis.qualco.countries.entity.Language;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LanguageMapper {
    LanguageDto map(Language source);
    List<LanguageDto> map(List<Language> source);
}
