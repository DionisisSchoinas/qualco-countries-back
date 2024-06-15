package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.CountryLanguage;
import com.dionisis.qualco.countries.entity.CountryLanguageId;
import com.dionisis.qualco.countries.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageId> {
    @Query(value = "SELECT cl.language FROM CountryLanguage cl WHERE cl.id.countryId = :countryId")
    List<Language> getLanguageByCountryId(Integer countryId);
}
