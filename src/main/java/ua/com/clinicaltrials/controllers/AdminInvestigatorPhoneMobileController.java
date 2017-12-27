package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.InvestigatorPhoneMobile;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorPhoneMobileRepository;

@RestController
@RequestMapping("/admin/investigator-phone-mobile-controller")
public class AdminInvestigatorPhoneMobileController {
    @Autowired
    InvestigatorPhoneMobileRepository investigatorPhoneMobileRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody InvestigatorPhoneMobile investigatorPhoneMobile){
        InvestigatorPhoneMobile currentInvestigatorPhoneMobile = investigatorPhoneMobileRepository.findOne(id);
        if (currentInvestigatorPhoneMobile == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. InvestigatorPhoneMobile with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorPhoneMobileRepository.save(currentInvestigatorPhoneMobile);
        return new ResponseEntity<>(currentInvestigatorPhoneMobile, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody InvestigatorPhoneMobile investigatorPhoneMobile){

        if (investigatorPhoneMobile == null){
            return new ResponseEntity<>(new CustomErrorType("No investigatorPhoneMobile"),HttpStatus.NOT_ACCEPTABLE);
        }


        investigatorPhoneMobileRepository.save(investigatorPhoneMobile);
        return new ResponseEntity<>(investigatorPhoneMobile, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        InvestigatorPhoneMobile investigatorPhoneMobile = investigatorPhoneMobileRepository.findOne(id);
        if (investigatorPhoneMobile == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "InvestigatorPhoneMobile with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorPhoneMobileRepository.delete(investigatorPhoneMobile);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
