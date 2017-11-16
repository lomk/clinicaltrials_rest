package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.InvestigatorFax;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorFaxRepository;

@RestController
@RequestMapping("/admin/")
public class AdminInvestigatorFaxController {
    @Autowired
    InvestigatorFaxRepository investigatorFaxRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody InvestigatorFax investigatorFax){
        InvestigatorFax currentInvestigatorFax = investigatorFaxRepository.findOne(id);
        if (currentInvestigatorFax == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. InvestigatorFax with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorFaxRepository.save(currentInvestigatorFax);
        return new ResponseEntity<>(currentInvestigatorFax, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody InvestigatorFax investigatorFax){

        if (investigatorFax == null){
            return new ResponseEntity<>(new CustomErrorType("No investigatorFax"),HttpStatus.NOT_ACCEPTABLE);
        }

        investigatorFaxRepository.save(investigatorFax);
        return new ResponseEntity<>(investigatorFax, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        InvestigatorFax investigatorFax = investigatorFaxRepository.findOne(id);
        if (investigatorFax == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "InvestigatorFax with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorFaxRepository.delete(investigatorFax);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
