package com.dionisis.qualco.countries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "languages", schema = "nation")
public class Language {
    @Id
    @Column(name = "language_id", nullable = false)
    private Integer id;

    @Column(name = "language", nullable = false, length = 50)
    private String language;

}