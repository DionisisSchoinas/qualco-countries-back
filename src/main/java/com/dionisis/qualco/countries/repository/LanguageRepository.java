package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
