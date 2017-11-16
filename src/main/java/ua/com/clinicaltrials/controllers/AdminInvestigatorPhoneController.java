package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.InvestigatorPhone;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorPhoneRepository;

@RestController
@RequestMapping("/admin/")
public class AdminInvestigatorPhoneController {
    @Autowired
    InvestigatorPhoneRepository investigatorPhoneRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody InvestigatorPhone investigatorPhone){
        InvestigatorPhone currentInvestigatorPhone = investigatorPhoneRepository.findOne(id);
        if (currentInvestigatorPhone == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. InvestigatorPhone with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorPhoneRepository.save(currentInvestigatorPhone);
        return new ResponseEntity<>(currentInvestigatorPhone, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody InvestigatorPhone investigatorPhone){

        if (investigatorPhone == null){
            return new ResponseEntity<>(new CustomErrorType("No investigatorPhone"),HttpStatus.NOT_ACCEPTABLE);
        }

        investigatorPhoneRepository.save(investigatorPhone);
        return new ResponseEntity<>(investigatorPhone, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        InvestigatorPhone investigatorPhone = investigatorPhoneRepository.findOne(id);
        if (investigatorPhone == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "InvestigatorPhone with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorPhoneRepository.delete(investigatorPhone);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
