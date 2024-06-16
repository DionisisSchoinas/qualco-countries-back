package com.dionisis.qualco.countries.controller;

import com.dionisis.qualco.countries.dto.CountryDto;
import com.dionisis.qualco.countries.dto.CountryGdpDto;
import com.dionisis.qualco.countries.dto.LanguageDto;
import com.dionisis.qualco.countries.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/counties")
public class CountryController {

    private final SearchService searchService;

    @Autowired
    public CountryController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<CountryDto> getAllCountries() {
        return searchService.getAllCountries();
    }

    @GetMapping("/{countryId}/languages")
    public List<LanguageDto> getSpokenLanguages(@PathVariable(name = "countryId") Integer countryId) {
        return searchService.getSpokenLanguages(countryId);
    }

    @GetMapping("/{countryId}/gdp")
    public List<CountryGdpDto> getGdpStats(@PathVariable(name = "countryId") Integer countryId) {
        return searchService.getGdpStatsForCountry(countryId);
    }
}
