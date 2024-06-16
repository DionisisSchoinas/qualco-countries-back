package com.dionisis.qualco.countries.controller;

import com.dionisis.qualco.countries.controller.param.RegionStatsParams;
import com.dionisis.qualco.countries.dto.CountryStatsParamsDto;
import com.dionisis.qualco.countries.dto.RegionTableStatsDto;
import com.dionisis.qualco.countries.dto.RegionDto;
import com.dionisis.qualco.countries.mapper.CountryStatsParamsMapper;
import com.dionisis.qualco.countries.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<RegionTableStatsDto> getCountryStatsTable(@RequestBody RegionStatsParams regionStatsParams) {
        CountryStatsParamsDto paramsDto = countryStatsParamsMapper.map(regionStatsParams);
        return searchService.getCountryStatsTable(paramsDto);
    }
}
