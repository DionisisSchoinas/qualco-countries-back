package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.CountryStat;
import com.dionisis.qualco.countries.entity.CountryStatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryStatRepository extends JpaRepository<CountryStat, CountryStatId> {
}
