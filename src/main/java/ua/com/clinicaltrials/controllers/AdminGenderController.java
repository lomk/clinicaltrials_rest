package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Gender;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.GenderRepository;

@RestController
@RequestMapping("/admin/")
public class AdminGenderController {
    @Autowired
    GenderRepository genderRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Gender gender){
        Gender currentGender = genderRepository.findOne(id);
        if (currentGender == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Gender with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        genderRepository.save(currentGender);
        return new ResponseEntity<>(currentGender, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Gender gender){

        if (gender == null){
            return new ResponseEntity<>(new CustomErrorType("No gender"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (gender.getNameUa() == null ||
                gender.getNameEn() == null ||
                gender.getNameRu() == null ||
                gender.getNameUa().isEmpty() ||
                gender.getNameEn().isEmpty() ||
                gender.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No gender name"),HttpStatus.NOT_ACCEPTABLE);
        }

        genderRepository.save(gender);
        return new ResponseEntity<>(gender, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Gender gender = genderRepository.findOne(id);
        if (gender == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Gender with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            genderRepository.delete(gender);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
