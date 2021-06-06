package com.cognizant.ormlearn22.repository;

import com.cognizant.ormlearn22.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    List<Country> findByNameContaining(String str);
    List<Country> findByNameContainingOrderByNameAsc(String str);
    List<Country> findByNameStartingWith(String s);

}
