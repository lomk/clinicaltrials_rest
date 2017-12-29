package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.City;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CityRepository;

@RestController
@RequestMapping("/admin/study-identifier")
public class AdminStudyIdentifierController {
    @Autowired
    CityRepository studyIdentifierRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody City studyIdentifier){
        City currentCity = studyIdentifierRepository.findOne(id);
        if (currentCity == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. City with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyIdentifierRepository.save(currentCity);
        return new ResponseEntity<>(currentCity, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody City studyIdentifier){

        if (studyIdentifier == null){
            return new ResponseEntity<>(new CustomErrorType("No studyIdentifier"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyIdentifier.getNameUa() == null ||
                studyIdentifier.getNameEn() == null ||
                studyIdentifier.getNameRu() == null ||
                studyIdentifier.getNameUa().isEmpty() ||
                studyIdentifier.getNameEn().isEmpty() ||
                studyIdentifier.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyIdentifier name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyIdentifierRepository.save(studyIdentifier);
        return new ResponseEntity<>(studyIdentifier, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        City studyIdentifier = studyIdentifierRepository.findOne(id);
        if (studyIdentifier == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "City with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyIdentifierRepository.delete(studyIdentifier);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
