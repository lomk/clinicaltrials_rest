package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MedicalForm;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MedicalFormRepository;

@RestController
@RequestMapping("/admin/medical-form")
public class AdminMedicalFormController {
    @Autowired
    MedicalFormRepository medicalFormRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedicalForm medicalForm){
        MedicalForm currentMedicalForm = medicalFormRepository.findOne(id);
        if (currentMedicalForm == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MedicalForm with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        medicalFormRepository.save(currentMedicalForm);
        return new ResponseEntity<>(currentMedicalForm, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MedicalForm medicalForm){

        if (medicalForm == null){
            return new ResponseEntity<>(new CustomErrorType("No medicalForm"),HttpStatus.NOT_ACCEPTABLE);
        }


        medicalFormRepository.save(medicalForm);
        return new ResponseEntity<>(medicalForm, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MedicalForm medicalForm = medicalFormRepository.findOne(id);
        if (medicalForm == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MedicalForm with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            medicalFormRepository.delete(medicalForm);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
