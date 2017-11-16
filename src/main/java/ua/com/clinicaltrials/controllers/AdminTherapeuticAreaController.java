package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.TherapeuticArea;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.TherapeuticAreaRepository;

@RestController
@RequestMapping("/admin/")
public class AdminTherapeuticAreaController {
    @Autowired
    TherapeuticAreaRepository therapeuticAreaRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody TherapeuticArea therapeuticArea){
        TherapeuticArea currentTherapeuticArea = therapeuticAreaRepository.findOne(id);
        if (currentTherapeuticArea == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. TherapeuticArea with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        therapeuticAreaRepository.save(currentTherapeuticArea);
        return new ResponseEntity<>(currentTherapeuticArea, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody TherapeuticArea therapeuticArea){

        if (therapeuticArea == null){
            return new ResponseEntity<>(new CustomErrorType("No therapeuticArea"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (therapeuticArea.getNameUa() == null ||
                therapeuticArea.getNameEn() == null ||
                therapeuticArea.getNameRu() == null ||
                therapeuticArea.getNameUa().isEmpty() ||
                therapeuticArea.getNameEn().isEmpty() ||
                therapeuticArea.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No therapeuticArea name"),HttpStatus.NOT_ACCEPTABLE);
        }

        therapeuticAreaRepository.save(therapeuticArea);
        return new ResponseEntity<>(therapeuticArea, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        TherapeuticArea therapeuticArea = therapeuticAreaRepository.findOne(id);
        if (therapeuticArea == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "TherapeuticArea with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            therapeuticAreaRepository.delete(therapeuticArea);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
