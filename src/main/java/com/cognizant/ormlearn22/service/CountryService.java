package com.cognizant.ormlearn22.service;

import com.cognizant.ormlearn22.model.Country;
import com.cognizant.ormlearn22.repository.CountryRepository;
import com.cognizant.ormlearn22.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public void addCountry(Country country) {

        if (!countryRepository.existsById(country.getCode())) {
            LOGGER.info("****************** Country Added, code is {}, name is {}! *******************", country.getCode(), country.getName());
            countryRepository.save(country);
        } else {
            LOGGER.info("****************** Country code already exists! *******************");
        }
    }

    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        java.util.Optional<Country> result = countryRepository.findById(countryCode);

        if (result.isEmpty())
            throw new CountryNotFoundException();
        else
            return result.get();
    }

    public void updateCountry(String countryCode,String countryName) {

        Optional<Country> result = countryRepository.findById(countryCode);
        if(result.isPresent()){
            Country c = result.get();
            c.setName(countryName);
            countryRepository.save(c);
            LOGGER.info("****************** Updated Successfully! ,{ }*******************",countryName);
        }
        else
            LOGGER.info("****************** Country Code Doesn't Exist! Updated UnSuccessfully *******************");
    }

    public void deleteCountry(String countryCode)
    {
        Optional<Country> result = countryRepository.findById(countryCode);
        if(result.isPresent()){
            countryRepository.deleteById(countryCode);
            LOGGER.info("****************** Deleted Country ,{}! *******************",countryCode);
        }
        else
            LOGGER.info("****************** Country Code Doesn't Exist! Delete UnSuccessful *******************");
    }

    public List<Country> findByNameContaining(String str){
        return countryRepository.findByNameContaining(str);
    }

    public List<Country> findByNameContainingOrderByAsc(String str){
        return countryRepository.findByNameContainingOrderByNameAsc(str);
    }

    public List<Country> findByNameStartingWith(String str){
        return countryRepository.findByNameStartingWith(str);
    }
}

