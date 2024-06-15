package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
