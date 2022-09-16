package com.example.datawork.repo;

import com.example.datawork.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {
    Country findByCountryName(String countryName);
}
