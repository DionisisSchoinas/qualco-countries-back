package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
