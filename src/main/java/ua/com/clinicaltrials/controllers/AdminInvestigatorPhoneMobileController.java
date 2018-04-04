package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MobilePhone;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigatorPhoneMobileRepository;

@RestController
@RequestMapping("/admin/investigator-phone-mobile-controller")
public class AdminInvestigatorPhoneMobileController {
    @Autowired
    InvestigatorPhoneMobileRepository investigatorPhoneMobileRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MobilePhone mobilePhone){
        MobilePhone currentMobilePhone = investigatorPhoneMobileRepository.findOne(id);
        if (currentMobilePhone == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MobilePhone with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigatorPhoneMobileRepository.save(currentMobilePhone);
        return new ResponseEntity<>(currentMobilePhone, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MobilePhone mobilePhone){

        if (mobilePhone == null){
            return new ResponseEntity<>(new CustomErrorType("No mobilePhone"),HttpStatus.NOT_ACCEPTABLE);
        }


        investigatorPhoneMobileRepository.save(mobilePhone);
        return new ResponseEntity<>(mobilePhone, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MobilePhone mobilePhone = investigatorPhoneMobileRepository.findOne(id);
        if (mobilePhone == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MobilePhone with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigatorPhoneMobileRepository.delete(mobilePhone);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
