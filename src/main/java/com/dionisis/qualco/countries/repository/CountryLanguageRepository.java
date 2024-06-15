package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.CountryLanguage;
import com.dionisis.qualco.countries.entity.CountryLanguageId;
import org.springframework.data.repository.CrudRepository;

public interface CountryLanguageRepository extends CrudRepository<CountryLanguage, CountryLanguageId> {
}
