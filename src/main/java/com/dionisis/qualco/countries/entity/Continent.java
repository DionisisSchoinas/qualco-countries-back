package com.dionisis.qualco.countries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "continents", schema = "nation")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}