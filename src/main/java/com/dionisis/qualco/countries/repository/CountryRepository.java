package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.dto.CountryGdpDto;
import com.dionisis.qualco.countries.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT new com.dionisis.qualco.countries.dto.CountryGdpDto(c.id, c.name, c.countryCode3, cs.id.year, cs.population, cs.gdp) " +
            "FROM Country c INNER JOIN CountryStat cs ON c.id = cs.id.countryId")
    List<CountryGdpDto> getGdpStatsForCountry();
}
