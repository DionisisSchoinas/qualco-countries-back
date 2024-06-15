package com.dionisis.qualco.countries.repository;

import com.dionisis.qualco.countries.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
}
