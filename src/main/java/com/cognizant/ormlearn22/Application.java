package com.cognizant.ormlearn22;

import com.cognizant.ormlearn22.model.Country;
import com.cognizant.ormlearn22.service.CountryService;
import com.cognizant.ormlearn22.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CountryService countryService;

//	@Autowired
//	public Application(CountryService countryService){
//		this.countryService=countryService;
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Override
	public void run(String... args) throws CountryNotFoundException {
		testGetAllCountries();
//		testAddCountry();
//		testUpdateCountry();
//		testGetCountryByCode();
//		testDeleteCountry();
		testFindByNameContaining();
		testFindByNameContainingOrderByAsc();
		testFindByNameStartingWith();
	}

	public void testGetAllCountries() {
		LOGGER.info("****************testGetAllCountries method started *******************************");
		List<Country> countries = countryService.getAllCountries();
		countries.forEach( c-> LOGGER.info(c.getCode()+" : "+c.getName()));
		LOGGER.info("****************testGetAllCountries method ended ******************************");
	}

	private void testGetCountryByCode() throws CountryNotFoundException {
		LOGGER.info("****************testGetCountriesByCode method started *******************************");
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country by Code={}", country);
		LOGGER.info("****************testGetCountriesByCode method ended ******************************");
	}

	public void testAddCountry() throws CountryNotFoundException{
		LOGGER.info("******************testAddCountry method started *******************");
		Country country = Country.builder().code("12").name("ging").build();
		countryService.addCountry(country);
		LOGGER.info("******************testAddCountry method ended *******************");
	}

	public void testUpdateCountry() {
		LOGGER.info("******************test updated Country method started *******************");
		countryService.updateCountry("KT", "kingsTown");
		LOGGER.info("******************testUpdateCountry method ended *******************");
	}

	public void testDeleteCountry() {
		LOGGER.info("***************testDeleteCountry method started *******************");
		countryService.deleteCountry("KT");
		LOGGER.info("***************testDeleteCountry method ended *******************");
	}

	private void testFindByNameContaining() {

		LOGGER.info("***************testFindByNameContaining method started *******************");
		LOGGER.info("Checking for country containing CY in the name");
		List<Country> cList = countryService.findByNameContaining("jib");
		for (Country c : cList)
//			LOGGER.info(c.getCode() + " -> " + c.getName());
			System.out.println(c.getCode()+" -> "+ c.getName());
		LOGGER.info("***************testFindByNameContaining method ended *******************");
	}

	private void testFindByNameContainingOrderByAsc() {

		List<Country> cList = countryService.findByNameContainingOrderByAsc("jip");
		System.out.println("------------------------COUNTRIES CONTAINING ou IN NAME IN ASCENDING ORDER-------------------");
		for (Country c : cList)
			System.out.println(c.getCode() + " -> " + c.getName());
		System.out.println("-------------------------------------------------------------------");
	}

	private void testFindByNameStartingWith() {
		List<Country> list = countryService.findByNameStartingWith("i");
		System.out.println("-------------------------------COUNTRIES STARTING WITH 'z'----------------------------------- ");
		for (Country c : list)
			System.out.println(c.getCode() + " -> " + c.getName());
		System.out.println("--------------------------------------------------------------------");
	}
}
