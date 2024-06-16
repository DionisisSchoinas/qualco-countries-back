package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.dto.CountryTableStatsDto;
import com.dionisis.qualco.countries.dto.RegionDto;
import com.dionisis.qualco.countries.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query(value =
            "SELECT new com.dionisis.qualco.countries.dto.CountryTableStatsDto(r.continent.name, r.name, c.name, cs.id.year, cs.population, cs.gdp) " +
                    "FROM Region r INNER JOIN Country c ON r = c.region INNER JOIN CountryStat cs ON c.id = cs.id.countryId WHERE r.id = :regionId"
    )
    List<CountryTableStatsDto> getCountryStatsTable(Integer regionId);

    @Query(value = "SELECT new com.dionisis.qualco.countries.dto.RegionDto(r.id, r.name) FROM Region r")
    List<RegionDto> getAllRegions();
}
