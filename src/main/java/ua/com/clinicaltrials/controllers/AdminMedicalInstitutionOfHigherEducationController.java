package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MedicalInstitutionOfHigherEducation;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MedicalInstitutionOfHigherEducationRepository;

@RestController
@RequestMapping("/admin/medical-institution-of-higher-education")
public class AdminMedicalInstitutionOfHigherEducationController {
    @Autowired
    MedicalInstitutionOfHigherEducationRepository medicalInstitutionOfHigherEducationRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedicalInstitutionOfHigherEducation medicalInstitutionOfHigherEducation){
        MedicalInstitutionOfHigherEducation currentMedicalInstitutionOfHigherEducation = medicalInstitutionOfHigherEducationRepository.findOne(id);
        if (currentMedicalInstitutionOfHigherEducation == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MedicalInstitutionOfHigherEducation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        medicalInstitutionOfHigherEducationRepository.save(currentMedicalInstitutionOfHigherEducation);
        return new ResponseEntity<>(currentMedicalInstitutionOfHigherEducation, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MedicalInstitutionOfHigherEducation medicalInstitutionOfHigherEducation){

        if (medicalInstitutionOfHigherEducation == null){
            return new ResponseEntity<>(new CustomErrorType("No medicalInstitutionOfHigherEducation"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (medicalInstitutionOfHigherEducation.getNameUa() == null ||
                medicalInstitutionOfHigherEducation.getNameEn() == null ||
                medicalInstitutionOfHigherEducation.getNameRu() == null ||
                medicalInstitutionOfHigherEducation.getNameUa().isEmpty() ||
                medicalInstitutionOfHigherEducation.getNameEn().isEmpty() ||
                medicalInstitutionOfHigherEducation.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No medicalInstitutionOfHigherEducation name"),HttpStatus.NOT_ACCEPTABLE);
        }

        medicalInstitutionOfHigherEducationRepository.save(medicalInstitutionOfHigherEducation);
        return new ResponseEntity<>(medicalInstitutionOfHigherEducation, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MedicalInstitutionOfHigherEducation medicalInstitutionOfHigherEducation = medicalInstitutionOfHigherEducationRepository.findOne(id);
        if (medicalInstitutionOfHigherEducation == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MedicalInstitutionOfHigherEducation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            medicalInstitutionOfHigherEducationRepository.delete(medicalInstitutionOfHigherEducation);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
