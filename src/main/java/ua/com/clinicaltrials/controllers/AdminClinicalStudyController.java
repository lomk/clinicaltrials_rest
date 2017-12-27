package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.ClinicalStudy;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ClinicalStudyRepository;

@RestController
@RequestMapping("/admin/clinical-study")
public class AdminClinicalStudyController {
    @Autowired
    ClinicalStudyRepository clinicalStudyRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClinicalStudy clinicalStudy){
        ClinicalStudy currentClinicalStudy = clinicalStudyRepository.findOne(id);
        if (currentClinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to update. ClinicalStudy with id " + id + " not found."),
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
        if (clinicalStudy.getStudyIdentifier() == null ||
                clinicalStudy.getStudyGeneralInformation() == null ||
                clinicalStudy.getCroUkraine() == null ||
                clinicalStudy.getEligibility() == null ||
                clinicalStudy.getSponsor() == null ||
                clinicalStudy.getTrialSite() == null
                ) {
            return new ResponseEntity(new CustomErrorType("Wrong data"),HttpStatus.NOT_ACCEPTABLE);
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
