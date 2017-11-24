package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.City;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CityRepository;

@RestController
@RequestMapping("/admin/city")
public class AdminCityController {

    @Autowired
    CityRepository cityRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody City city){
        City currentCity = cityRepository.findOne(id);
        if (currentCity == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. City with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        cityRepository.save(currentCity);
        return new ResponseEntity<>(currentCity, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody City city){

        if (city == null){
            return new ResponseEntity<>(new CustomErrorType("No city"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (city.getNameUa() == null ||
                city.getNameEn() == null ||
                city.getNameRu() == null ||
                city.getNameUa().isEmpty() ||
                city.getNameEn().isEmpty() ||
                city.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No city name"),HttpStatus.NOT_ACCEPTABLE);
        }

        cityRepository.save(city);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        City city = cityRepository.findOne(id);
        if (city == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "City with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            cityRepository.delete(city);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
