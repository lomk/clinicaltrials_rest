package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.ClinicalStudy;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ClinicalStudyRepository;

public class AdminClinicalStudyController {
    @Autowired
    ClinicalStudyRepository clinicalStudyRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClinicalStudy clinicalStudy){
        ClinicalStudy currentClinicalStudy = clinicalStudyRepository.findOne(id);
        if (currentClinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. ClinicalStudy with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        clinicalStudyRepository.save(currentClinicalStudy);
        return new ResponseEntity<>(currentClinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody ClinicalStudy clinicalStudy){

        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType("No clinicalStudy"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (clinicalStudy.getNameUa() == null ||
                clinicalStudy.getNameEn() == null ||
                clinicalStudy.getNameRu() == null ||
                clinicalStudy.getNameUa().isEmpty() ||
                clinicalStudy.getNameEn().isEmpty() ||
                clinicalStudy.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No clinicalStudy name"),HttpStatus.NOT_ACCEPTABLE);
        }

        clinicalStudyRepository.save(clinicalStudy);
        return new ResponseEntity<>(clinicalStudy, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findOne(id);
        if (clinicalStudy == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            clinicalStudyRepository.delete(clinicalStudy);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
