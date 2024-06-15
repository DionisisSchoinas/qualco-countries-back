package com.dionisis.qualco.countries.service;

import com.dionisis.qualco.countries.dto.CountryDto;
import com.dionisis.qualco.countries.dto.LanguageDto;
import com.dionisis.qualco.countries.entity.Country;
import com.dionisis.qualco.countries.entity.Language;
import com.dionisis.qualco.countries.mapper.CountryMapper;
import com.dionisis.qualco.countries.mapper.LanguageMapper;
import com.dionisis.qualco.countries.repository.CountryLanguageRepository;
import com.dionisis.qualco.countries.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryMapper countryMapper;
    private final LanguageMapper languageMapper;
    private final CountryRepository countryRepository;
    private final CountryLanguageRepository countryLanguageRepository;

    @Autowired
    public CountryService(CountryMapper countryMapper, LanguageMapper languageMapper, CountryRepository countryRepository, CountryLanguageRepository countryLanguageRepository) {
        this.countryMapper = countryMapper;
        this.languageMapper = languageMapper;
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
    }

    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.map(countries);
    }

    public List<LanguageDto> getSpokenLanguages(Integer countryId) {
        List<Language> languages = countryLanguageRepository.getLanguageByCountryId(countryId);
        return languageMapper.map(languages);
    }
}
