package com.dionisis.qualco.countries.controller;

import com.dionisis.qualco.countries.controller.param.PaginationRequest;
import com.dionisis.qualco.countries.dto.CountryDto;
import com.dionisis.qualco.countries.dto.CountryGdpDto;
import com.dionisis.qualco.countries.dto.LanguageDto;
import com.dionisis.qualco.countries.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

    private final SearchService searchService;

    @Autowired
    public CountryController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<CountryDto> getAllCountries(@RequestBody PaginationRequest pageRequest) {
        return searchService.getAllCountries(pageRequest);
    }

    @GetMapping("/{countryId}/languages")
    public List<LanguageDto> getSpokenLanguages(@PathVariable(name = "countryId") Integer countryId) {
        return searchService.getSpokenLanguages(countryId);
    }

    @GetMapping("/gdp")
    public List<CountryGdpDto> getGdpStats() {
        return searchService.getGdpStatsForCountry();
    }
}
