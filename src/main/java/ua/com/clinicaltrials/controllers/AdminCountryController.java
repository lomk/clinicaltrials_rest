package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Country;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CountryRepository;

public class AdminCountryController {
    @Autowired
    CountryRepository countryRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Country country){
        Country currentCountry = countryRepository.findOne(id);
        if (currentCountry == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Country with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        countryRepository.save(currentCountry);
        return new ResponseEntity<>(currentCountry, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Country country){

        if (country == null){
            return new ResponseEntity<>(new CustomErrorType("No country"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (country.getNameUa() == null ||
                country.getNameEn() == null ||
                country.getNameRu() == null ||
                country.getNameUa().isEmpty() ||
                country.getNameEn().isEmpty() ||
                country.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No country name"),HttpStatus.NOT_ACCEPTABLE);
        }

        countryRepository.save(country);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Country country = countryRepository.findOne(id);
        if (country == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Country with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            countryRepository.delete(country);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
