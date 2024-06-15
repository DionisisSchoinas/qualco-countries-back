package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Country;
import com.dionisis.qualco.countries.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
