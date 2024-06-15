package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}
