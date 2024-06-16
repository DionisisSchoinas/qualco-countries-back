package com.dionisis.qualco.countries.service;

import com.dionisis.qualco.countries.dto.*;
import com.dionisis.qualco.countries.entity.Country;
import com.dionisis.qualco.countries.entity.Language;
import com.dionisis.qualco.countries.mapper.CountryMapper;
import com.dionisis.qualco.countries.mapper.LanguageMapper;
import com.dionisis.qualco.countries.repository.CountryLanguageRepository;
import com.dionisis.qualco.countries.repository.CountryRepository;
import com.dionisis.qualco.countries.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    private final CountryMapper countryMapper;
    private final LanguageMapper languageMapper;
    private final CountryRepository countryRepository;
    private final CountryLanguageRepository countryLanguageRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public SearchService(CountryMapper countryMapper, LanguageMapper languageMapper, CountryRepository countryRepository, CountryLanguageRepository countryLanguageRepository, RegionRepository regionRepository) {
        this.countryMapper = countryMapper;
        this.languageMapper = languageMapper;
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
        this.regionRepository = regionRepository;
    }

    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.map(countries);
    }

    public List<LanguageDto> getSpokenLanguages(Integer countryId) {
        List<Language> languages = countryLanguageRepository.getLanguageByCountryId(countryId);
        return languageMapper.map(languages);
    }

    public List<CountryGdpDto> getGdpStatsForCountry(Integer countryId) {
        return countryRepository.getGdpStatsForCountry(countryId);
    }

    public List<CountryTableStatsDto> getCountryStatsTable(CountryStatsParamsDto paramsDto) {
        return regionRepository.getCountryStatsTable(paramsDto.getRegionId());
    }

    public List<RegionDto> getAllRegions() {
        return regionRepository.getAllRegions();
    }
}
