package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.CroUkraine;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CroUkraineRepository;

@RestController
@RequestMapping("/admin/")
public class AdminCroUkraineController {
    @Autowired
    CroUkraineRepository croUkraineRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CroUkraine croUkraine){
        CroUkraine currentCroUkraine = croUkraineRepository.findOne(id);
        if (currentCroUkraine == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. CroUkraine with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        croUkraineRepository.save(currentCroUkraine);
        return new ResponseEntity<>(currentCroUkraine, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody CroUkraine croUkraine){

        if (croUkraine == null){
            return new ResponseEntity<>(new CustomErrorType("No croUkraine"),HttpStatus.NOT_ACCEPTABLE);
        }


        croUkraineRepository.save(croUkraine);
        return new ResponseEntity<>(croUkraine, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        CroUkraine croUkraine = croUkraineRepository.findOne(id);
        if (croUkraine == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "CroUkraine with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            croUkraineRepository.delete(croUkraine);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
