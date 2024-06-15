package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
}
