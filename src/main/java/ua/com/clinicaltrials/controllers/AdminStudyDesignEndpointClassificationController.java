package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignEndpointClassification;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignEndpointClassificationRepository;

@RestController
@RequestMapping("/admin/")
public class AdminStudyDesignEndpointClassificationController {
    @Autowired
    StudyDesignEndpointClassificationRepository cityRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignEndpointClassification city){
        StudyDesignEndpointClassification currentStudyDesignEndpointClassification = cityRepository.findOne(id);
        if (currentStudyDesignEndpointClassification == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignEndpointClassification with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        cityRepository.save(currentStudyDesignEndpointClassification);
        return new ResponseEntity<>(currentStudyDesignEndpointClassification, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignEndpointClassification city){

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
        StudyDesignEndpointClassification city = cityRepository.findOne(id);
        if (city == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignEndpointClassification with id " + id + " not found."),
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
