package com.dionisis.qualco.countries.service;

import com.dionisis.qualco.countries.controller.param.PaginationRequest;
import com.dionisis.qualco.countries.dto.*;
import com.dionisis.qualco.countries.entity.*;
import com.dionisis.qualco.countries.mapper.CountryMapper;
import com.dionisis.qualco.countries.mapper.LanguageMapper;
import com.dionisis.qualco.countries.repository.CountryLanguageRepository;
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
    private final CountryLanguageRepository countryLanguageRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public SearchService(EntityManager em, CountryMapper countryMapper, LanguageMapper languageMapper, CountryLanguageRepository countryLanguageRepository, RegionRepository regionRepository) {
        this.em = em;
        this.countryMapper = countryMapper;
        this.languageMapper = languageMapper;
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
        tq.setFirstResult(pageRequest.getPage() * pageRequest.getSize());
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
        tq.setFirstResult(pageRequest.getPage() * pageRequest.getSize());
        tq.setMaxResults(pageRequest.getSize());

        List<CountryGdpDto> countries = tq.getResultList();

        return new PageImpl<>(countries, PageRequest.of(pageRequest.getPage(), pageRequest.getSize()), count);
    }

    public Page<RegionTableStatsDto> getCountryStatsTable(CountryStatsParamsDto paramsDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Region> countRoot = countQuery.from(Region.class);
        Join<Country, CountryStat> countJoin = countRoot.join(Region_.countries, JoinType.INNER).join(Country_.countryStats, JoinType.INNER);
        countQuery
                .select(cb.count(countJoin))
                .where(constructPredicate(cb, countRoot, countJoin,paramsDto));
        Long count = em.createQuery(countQuery).getSingleResult();

        if (count == 0) {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(paramsDto.getPage(), paramsDto.getSize()), 0);
        }

        CriteriaQuery<RegionTableStatsDto> criteriaQuery = cb.createQuery(RegionTableStatsDto.class);
        Root<Region> root = criteriaQuery.from(Region.class);
        Join<Region, Country> countryJoin = root.join(Region_.countries, JoinType.INNER);
        Join<Country, CountryStat> countryStatJoin = countryJoin.join(Country_.countryStats, JoinType.INNER);

        CriteriaQuery<RegionTableStatsDto> cq = criteriaQuery.multiselect(
                root.join(Region_.continent).get(Continent_.name),
                root.get(Region_.name),
                countryJoin.get(Country_.name),
                countryStatJoin.get(CountryStat_.id).get(CountryStatId_.year),
                countryStatJoin.get(CountryStat_.population),
                countryStatJoin.get(CountryStat_.gdp)
        );
        cq = cq.where(constructPredicate(cb, root, countryStatJoin, paramsDto));

        TypedQuery<RegionTableStatsDto> tq = em.createQuery(cq.orderBy(cb.asc(root.get(Region_.name))));
        tq.setFirstResult(paramsDto.getPage() * paramsDto.getSize());
        tq.setMaxResults(paramsDto.getSize());

        List<RegionTableStatsDto> countries = tq.getResultList();

        return new PageImpl<>(countries, PageRequest.of(paramsDto.getPage(), paramsDto.getSize()), count);
    }

    private Predicate constructPredicate(CriteriaBuilder cb, Root<Region> root, Join<Country, CountryStat> join, CountryStatsParamsDto paramsDto) {
        Predicate predicate = cb.conjunction();
        if (paramsDto.getRegionId() != null) {
            predicate = cb.and(predicate, cb.equal(root.get(Region_.id), paramsDto.getRegionId()));
        }

        if (paramsDto.getDateFrom() != null) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(join.get(CountryStat_.id).get(CountryStatId_.year), paramsDto.getDateFrom().getYear()));
        }

        if (paramsDto.getDateTo() != null) {
            predicate = cb.and(predicate, cb.lessThanOrEqualTo(join.get(CountryStat_.id).get(CountryStatId_.year), paramsDto.getDateTo().getYear()));
        }
        return predicate;
    }

    public List<RegionDto> getAllRegions() {
        return regionRepository.getAllRegions();
    }
}
