package com.dionisis.qualco.countries.service;

import com.dionisis.qualco.countries.controller.param.PaginationRequest;
import com.dionisis.qualco.countries.dto.*;
import com.dionisis.qualco.countries.entity.*;
import com.dionisis.qualco.countries.mapper.CountryMapper;
import com.dionisis.qualco.countries.mapper.LanguageMapper;
import com.dionisis.qualco.countries.repository.CountryLanguageRepository;
import com.dionisis.qualco.countries.repository.CountryRepository;
import com.dionisis.qualco.countries.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private final EntityManager em;

    private final CountryMapper countryMapper;
    private final LanguageMapper languageMapper;
    private final CountryRepository countryRepository;
    private final CountryLanguageRepository countryLanguageRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public SearchService(EntityManager em, CountryMapper countryMapper, LanguageMapper languageMapper, CountryRepository countryRepository, CountryLanguageRepository countryLanguageRepository, RegionRepository regionRepository) {
        this.em = em;
        this.countryMapper = countryMapper;
        this.languageMapper = languageMapper;
        this.countryRepository = countryRepository;
        this.countryLanguageRepository = countryLanguageRepository;
        this.regionRepository = regionRepository;
    }

    public Page<CountryDto> getAllCountries(PaginationRequest pageRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Country.class)));
        Long count = em.createQuery(countQuery).getSingleResult();

        if (count == 0) {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(pageRequest.getPage(), pageRequest.getSize()), 0);
        }

        CriteriaQuery<Country> criteriaQuery = cb.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);

        TypedQuery<Country> tq = em.createQuery(criteriaQuery.select(root).orderBy(cb.asc(root.get(Country_.name))));
        tq.setFirstResult(pageRequest.getPage());
        tq.setMaxResults(pageRequest.getSize());

        List<Country> countries = tq.getResultList();

        return new PageImpl<>(countryMapper.map(countries), PageRequest.of(pageRequest.getPage(), pageRequest.getSize()), count);
    }

    public List<LanguageDto> getSpokenLanguages(Integer countryId) {
        List<Language> languages = countryLanguageRepository.getLanguageByCountryId(countryId);
        return languageMapper.map(languages);
    }

    public Page<CountryGdpDto> getGdpStatsForCountry(PaginationRequest pageRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Country.class).join(Country_.countryStats, JoinType.INNER)));
        Long count = em.createQuery(countQuery).getSingleResult();

        if (count == 0) {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(pageRequest.getPage(), pageRequest.getSize()), 0);
        }

        CriteriaQuery<CountryGdpDto> criteriaQuery = cb.createQuery(CountryGdpDto.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        Join<Country, CountryStat> join = root.join(Country_.countryStats, JoinType.INNER);
        CriteriaQuery<CountryGdpDto> cq = criteriaQuery.multiselect(
                root.get(Country_.id),
                root.get(Country_.name),
                root.get(Country_.countryCode3),
                join.get(CountryStat_.id).get(CountryStatId_.year),
                join.get(CountryStat_.population),
                join.get(CountryStat_.gdp)
        );

        TypedQuery<CountryGdpDto> tq = em.createQuery(cq.orderBy(cb.asc(root.get(Country_.name))));
        tq.setFirstResult(pageRequest.getPage());
        tq.setMaxResults(pageRequest.getSize());

        List<CountryGdpDto> countries = tq.getResultList();

        return new PageImpl<>(countries, PageRequest.of(pageRequest.getPage(), pageRequest.getSize()), count);
    }

    public List<RegionTableStatsDto> getCountryStatsTable(CountryStatsParamsDto paramsDto) {
        return regionRepository.getCountryStatsTable(paramsDto.getRegionId());
    }

    public List<RegionDto> getAllRegions() {
        return regionRepository.getAllRegions();
    }
}
