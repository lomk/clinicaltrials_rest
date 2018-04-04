package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Phone;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorPhoneRepository;

@RestController
@RequestMapping("/admin/investigator-phone-controller")
public class AdminInvestigatorPhoneController {
    @Autowired
    InvestigatorPhoneRepository investigatorPhoneRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Phone phone){
        Phone currentPhone = investigatorPhoneRepository.findOne(id);
        if (currentPhone == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Phone with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorPhoneRepository.save(currentPhone);
        return new ResponseEntity<>(currentPhone, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Phone phone){

        if (phone == null){
            return new ResponseEntity<>(new CustomErrorType("No phone"),HttpStatus.NOT_ACCEPTABLE);
        }

        investigatorPhoneRepository.save(phone);
        return new ResponseEntity<>(phone, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Phone phone = investigatorPhoneRepository.findOne(id);
        if (phone == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Phone with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorPhoneRepository.delete(phone);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
