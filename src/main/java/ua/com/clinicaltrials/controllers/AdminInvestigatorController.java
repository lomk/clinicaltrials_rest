package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Investigator;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorRepository;

@RestController
@RequestMapping("/admin/investigator")
public class AdminInvestigatorController {
    @Autowired
    InvestigatorRepository investigatorRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Investigator investigator){
        Investigator currentInvestigator = investigatorRepository.findOne(id);
        if (currentInvestigator == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Investigator with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorRepository.save(currentInvestigator);
        return new ResponseEntity<>(currentInvestigator, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Investigator investigator){

        if (investigator == null){
            return new ResponseEntity<>(new CustomErrorType("No investigator"),HttpStatus.NOT_ACCEPTABLE);
        }

        investigatorRepository.save(investigator);
        return new ResponseEntity<>(investigator, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Investigator investigator = investigatorRepository.findOne(id);
        if (investigator == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Investigator with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorRepository.delete(investigator);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
