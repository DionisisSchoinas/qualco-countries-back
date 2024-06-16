package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.dto.CountryGdpDto;
import com.dionisis.qualco.countries.dto.CountryStatDto;
import com.dionisis.qualco.countries.dto.CountryTableStatsDto;
import com.dionisis.qualco.countries.entity.Country;
import com.dionisis.qualco.countries.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT new com.dionisis.qualco.countries.dto.CountryGdpDto(c.name, c.countryCode3, cs.id.year, cs.population, cs.gdp) " +
            "FROM Country c INNER JOIN CountryStat cs ON c.id = cs.id.countryId WHERE c.id = :countryId")
    List<CountryGdpDto> getGdpStatsForCountry(Integer countryId);
}
