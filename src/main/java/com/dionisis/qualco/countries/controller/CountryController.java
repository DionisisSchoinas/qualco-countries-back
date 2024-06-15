package com.dionisis.qualco.countries.controller;

import com.dionisis.qualco.countries.dto.CountryDto;
import com.dionisis.qualco.countries.dto.LanguageDto;
import com.dionisis.qualco.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("countries")
    public List<CountryDto> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{countryId}/languages")
    public List<LanguageDto> getSpokenLanguages(@PathVariable(name = "countryId") Integer countryId) {
        return countryService.getSpokenLanguages(countryId);
    }
}
