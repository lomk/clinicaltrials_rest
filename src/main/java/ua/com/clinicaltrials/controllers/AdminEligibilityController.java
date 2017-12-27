package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Eligibility;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.EligibilityRepository;

@RestController
@RequestMapping("/admin/eligibility")
public class AdminEligibilityController {
    @Autowired
    EligibilityRepository eligibilityRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Eligibility eligibility){
        Eligibility currentEligibility = eligibilityRepository.findOne(id);
        if (currentEligibility == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Eligibility with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        eligibilityRepository.save(currentEligibility);
        return new ResponseEntity<>(currentEligibility, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Eligibility eligibility){

        if (eligibility == null){
            return new ResponseEntity<>(new CustomErrorType("No eligibility"),HttpStatus.NOT_ACCEPTABLE);
        }

        eligibilityRepository.save(eligibility);
        return new ResponseEntity<>(eligibility, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Eligibility eligibility = eligibilityRepository.findOne(id);
        if (eligibility == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Eligibility with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            eligibilityRepository.delete(eligibility);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
