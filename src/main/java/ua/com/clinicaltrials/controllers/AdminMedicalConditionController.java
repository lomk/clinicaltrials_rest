package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MedicalCondition;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MedicalConditionRepository;

@RestController
@RequestMapping("/admin/medical-condition")
public class AdminMedicalConditionController {
    @Autowired
    MedicalConditionRepository medicalConditionRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedicalCondition medicalCondition){
        MedicalCondition currentMedicalCondition = medicalConditionRepository.findOne(id);
        if (currentMedicalCondition == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MedicalCondition with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        medicalConditionRepository.save(currentMedicalCondition);
        return new ResponseEntity<>(currentMedicalCondition, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MedicalCondition medicalCondition){

        if (medicalCondition == null){
            return new ResponseEntity<>(new CustomErrorType("No medicalCondition"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (medicalCondition.getNameUa() == null ||
                medicalCondition.getNameEn() == null ||
                medicalCondition.getNameRu() == null ||
                medicalCondition.getNameUa().isEmpty() ||
                medicalCondition.getNameEn().isEmpty() ||
                medicalCondition.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No medicalCondition name"),HttpStatus.NOT_ACCEPTABLE);
        }

        medicalConditionRepository.save(medicalCondition);
        return new ResponseEntity<>(medicalCondition, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MedicalCondition medicalCondition = medicalConditionRepository.findOne(id);
        if (medicalCondition == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MedicalCondition with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            medicalConditionRepository.delete(medicalCondition);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
