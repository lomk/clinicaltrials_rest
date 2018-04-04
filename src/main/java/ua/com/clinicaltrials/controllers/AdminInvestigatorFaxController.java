package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Fax;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorFaxRepository;

@RestController
@RequestMapping("/admin/investigator-fax-controller")
public class AdminInvestigatorFaxController {
    @Autowired
    InvestigatorFaxRepository investigatorFaxRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Fax fax){
        Fax currentFax = investigatorFaxRepository.findOne(id);
        if (currentFax == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Fax with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorFaxRepository.save(currentFax);
        return new ResponseEntity<>(currentFax, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Fax fax){

        if (fax == null){
            return new ResponseEntity<>(new CustomErrorType("No fax"),HttpStatus.NOT_ACCEPTABLE);
        }

        investigatorFaxRepository.save(fax);
        return new ResponseEntity<>(fax, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Fax fax = investigatorFaxRepository.findOne(id);
        if (fax == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Fax with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorFaxRepository.delete(fax);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
