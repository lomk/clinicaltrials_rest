package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.AcceptHealthyVolunteers;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.AcceptHealthyVolunteersRepository;

@RestController
public class AdminAcceptHealthyVolunteersController {
    @Autowired
    AcceptHealthyVolunteersRepository acceptHealthyVolunteersRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AcceptHealthyVolunteers acceptHealthyVolunteers){
        AcceptHealthyVolunteers currentAcceptHealthyVolunteers = acceptHealthyVolunteersRepository.findOne(id);
        if (currentAcceptHealthyVolunteers == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. AcceptHealthyVolunteers with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        acceptHealthyVolunteersRepository.save(currentAcceptHealthyVolunteers);
        return new ResponseEntity<>(currentAcceptHealthyVolunteers, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody AcceptHealthyVolunteers acceptHealthyVolunteers){

        if (acceptHealthyVolunteers == null){
            return new ResponseEntity<>(new CustomErrorType("No acceptHealthyVolunteers"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (acceptHealthyVolunteers.getValueUa() == null ||
                acceptHealthyVolunteers.getValueEn() == null ||
                acceptHealthyVolunteers.getValueRu() == null ||
                acceptHealthyVolunteers.getValueUa().isEmpty() ||
                acceptHealthyVolunteers.getValueEn().isEmpty() ||
                acceptHealthyVolunteers.getValueRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No acceptHealthyVolunteers name"),HttpStatus.NOT_ACCEPTABLE);
        }

        acceptHealthyVolunteersRepository.save(acceptHealthyVolunteers);
        return new ResponseEntity<>(acceptHealthyVolunteers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        AcceptHealthyVolunteers acceptHealthyVolunteers = acceptHealthyVolunteersRepository.findOne(id);
        if (acceptHealthyVolunteers == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "AcceptHealthyVolunteers with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            acceptHealthyVolunteersRepository.delete(acceptHealthyVolunteers);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
