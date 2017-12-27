package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Dosage;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.DosageRepository;

@RestController
@RequestMapping("/admin/dosage")
public class AdminDosageController {
    @Autowired
    DosageRepository dosageRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Dosage dosage){
        Dosage currentDosage = dosageRepository.findOne(id);
        if (currentDosage == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Dosage with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        dosageRepository.save(currentDosage);
        return new ResponseEntity<>(currentDosage, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Dosage dosage){

        if (dosage == null){
            return new ResponseEntity<>(new CustomErrorType("No dosage"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (dosage.getNameUa() == null ||
                dosage.getNameEn() == null ||
                dosage.getNameRu() == null ||
                dosage.getNameUa().isEmpty() ||
                dosage.getNameEn().isEmpty() ||
                dosage.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No dosage name"),HttpStatus.NOT_ACCEPTABLE);
        }

        dosageRepository.save(dosage);
        return new ResponseEntity<>(dosage, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Dosage dosage = dosageRepository.findOne(id);
        if (dosage == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Dosage with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            dosageRepository.delete(dosage);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
