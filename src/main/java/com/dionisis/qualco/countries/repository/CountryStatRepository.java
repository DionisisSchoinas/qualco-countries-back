package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.CountryStat;
import com.dionisis.qualco.countries.entity.CountryStatId;
import org.springframework.data.repository.CrudRepository;

public interface CountryStatRepository extends CrudRepository<CountryStat, CountryStatId> {
}
