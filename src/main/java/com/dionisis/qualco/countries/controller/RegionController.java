package com.dionisis.qualco.countries.controller;

import com.dionisis.qualco.countries.controller.param.CountryStatsParams;
import com.dionisis.qualco.countries.dto.CountryStatsParamsDto;
import com.dionisis.qualco.countries.dto.CountryTableStatsDto;
import com.dionisis.qualco.countries.dto.RegionDto;
import com.dionisis.qualco.countries.mapper.CountryStatsParamsMapper;
import com.dionisis.qualco.countries.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    private final SearchService searchService;
    private final CountryStatsParamsMapper countryStatsParamsMapper;

    @Autowired
    public RegionController(SearchService searchService, CountryStatsParamsMapper countryStatsParamsMapper) {
        this.searchService = searchService;
        this.countryStatsParamsMapper = countryStatsParamsMapper;
    }

    @GetMapping
    public List<RegionDto> getAllRegions() {
        return searchService.getAllRegions();
    }

    @PostMapping
    public List<CountryTableStatsDto> getCountryStatsTable(@RequestBody CountryStatsParams countryStatsParams) {
        CountryStatsParamsDto paramsDto = countryStatsParamsMapper.map(countryStatsParams);
        return searchService.getCountryStatsTable(paramsDto);
    }
}
