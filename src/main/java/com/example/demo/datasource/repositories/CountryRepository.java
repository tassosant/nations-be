package com.example.demo.datasource.repositories;

import com.example.demo.datasource.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    List<CountryEntity> findAllByOrderByAreaDesc();
}
