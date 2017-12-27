package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.DrugManufacturer;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.DrugManufacturerRepository;

@RestController
@RequestMapping("/admin/drug-manufacturer")
public class AdminDrugManufacturerController {
    @Autowired
    DrugManufacturerRepository drugManufacturerRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody DrugManufacturer drugManufacturer){
        DrugManufacturer currentDrugManufacturer = drugManufacturerRepository.findOne(id);
        if (currentDrugManufacturer == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. DrugManufacturer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        drugManufacturerRepository.save(currentDrugManufacturer);
        return new ResponseEntity<>(currentDrugManufacturer, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody DrugManufacturer drugManufacturer){

        if (drugManufacturer == null){
            return new ResponseEntity<>(new CustomErrorType("No drugManufacturer"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (drugManufacturer.getNameUa() == null ||
                drugManufacturer.getNameEn() == null ||
                drugManufacturer.getNameRu() == null ||
                drugManufacturer.getNameUa().isEmpty() ||
                drugManufacturer.getNameEn().isEmpty() ||
                drugManufacturer.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No drugManufacturer name"),HttpStatus.NOT_ACCEPTABLE);
        }

        drugManufacturerRepository.save(drugManufacturer);
        return new ResponseEntity<>(drugManufacturer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        DrugManufacturer drugManufacturer = drugManufacturerRepository.findOne(id);
        if (drugManufacturer == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "DrugManufacturer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            drugManufacturerRepository.delete(drugManufacturer);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
