package com.example.demo.datasource.repositories;

import com.example.demo.datasource.entities.CountryEntity;
import com.example.demo.services.country.dtos.CountryMaxGdpDataProjection;
import com.example.demo.services.statistic.dtos.StatisticsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    List<CountryEntity> findAllByOrderByAreaDesc();

    @Query("select l.name from CountryLanguageEntity cl, LanguageEntity l where l.id=cl.language.id and cl.country.id=:countryId")
    List<String> getCountryLanguages(Integer countryId);

    @Query(value = """
            SELECT
                ranked.country_id AS countryId,
                c.name AS countryName,
                ranked.year AS year,
                ranked.population AS population,
                ranked.gdp AS gdp
            FROM (
                SELECT
                    cs.country_id,
                    cs.year,
                    cs.population,
                    cs.gdp,
                    ROW_NUMBER() OVER (
                        PARTITION BY cs.country_id
                        ORDER BY cs.gdp / cs.population DESC
                    ) AS rn
                FROM country_stats cs
                WHERE cs.population > 0
            ) ranked
            JOIN countries c
                ON c.country_id = ranked.country_id
            WHERE ranked.rn = 1
            """,
            nativeQuery = true)
    List<CountryMaxGdpDataProjection> findCountriesWithMaxGdpPerPopulation();

    @Query(value = """
            SELECT 
                c.country_id AS countryId,
                con.name AS continentName,
                r.name AS regionName,
                c.name AS countryName,
                cs.year AS year,
                cs.population AS population,
                cs.gdp AS gdp
            FROM countries c, country_stats cs, regions r, continents con
            WHERE c.country_id=cs.country_id AND c.region_id=r.region_id AND r.continent_id=con.continent_id AND cs.country_id=c.country_id AND r.region_id IN (:regionIds)
            """, nativeQuery = true)
    List<StatisticsProjection> findStatistics(List<Integer> regionIds);

    @Query(value = """
            SELECT 
                c.country_id AS countryId,
                con.name AS continentName,
                r.name AS regionName,
                c.name AS countryName,
                cs.year AS year,
                cs.population AS population,
                cs.gdp AS gdp
            FROM countries c, country_stats cs, regions r, continents con
            WHERE c.country_id=cs.country_id AND c.region_id=r.region_id AND r.continent_id=con.continent_id AND cs.country_id=c.country_id AND r.region_id IN (:regionIds) AND cs.year BETWEEN :yearFrom AND :yearTO 
            """, nativeQuery = true)
    List<StatisticsProjection> findStatistics(List<Integer> regionIds, Integer yearFrom, Integer yearTo);


}
