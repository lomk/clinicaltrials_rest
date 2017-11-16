package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MedicalInstitution;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MedicalInstitutionRepository;

@RestController
@RequestMapping("/admin/")
public class AdminMedicalInstitutionController {
    @Autowired
    MedicalInstitutionRepository medicalInstitutionRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedicalInstitution medicalInstitution){
        MedicalInstitution currentMedicalInstitution = medicalInstitutionRepository.findOne(id);
        if (currentMedicalInstitution == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MedicalInstitution with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        medicalInstitutionRepository.save(currentMedicalInstitution);
        return new ResponseEntity<>(currentMedicalInstitution, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MedicalInstitution medicalInstitution){

        if (medicalInstitution == null){
            return new ResponseEntity<>(new CustomErrorType("No medicalInstitution"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (medicalInstitution.getNameUa() == null ||
                medicalInstitution.getNameEn() == null ||
                medicalInstitution.getNameRu() == null ||
                medicalInstitution.getNameUa().isEmpty() ||
                medicalInstitution.getNameEn().isEmpty() ||
                medicalInstitution.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No medicalInstitution name"),HttpStatus.NOT_ACCEPTABLE);
        }

        medicalInstitutionRepository.save(medicalInstitution);
        return new ResponseEntity<>(medicalInstitution, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MedicalInstitution medicalInstitution = medicalInstitutionRepository.findOne(id);
        if (medicalInstitution == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MedicalInstitution with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            medicalInstitutionRepository.delete(medicalInstitution);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
