package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MobilePhoneCode;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.PhoneMobileCodeRepository;

@RestController
@RequestMapping("/admin/phone-mobile-code-controller")
public class AdminPhoneMobileCodeController {
    @Autowired
    PhoneMobileCodeRepository phoneMobileCodeRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MobilePhoneCode mobilePhoneCode){
        MobilePhoneCode currentMobilePhoneCode = phoneMobileCodeRepository.findOne(id);
        if (currentMobilePhoneCode == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MobilePhoneCode with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        phoneMobileCodeRepository.save(currentMobilePhoneCode);
        return new ResponseEntity<>(currentMobilePhoneCode, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MobilePhoneCode mobilePhoneCode){

        if (mobilePhoneCode == null){
            return new ResponseEntity<>(new CustomErrorType("No mobilePhoneCode"),HttpStatus.NOT_ACCEPTABLE);
        }


        phoneMobileCodeRepository.save(mobilePhoneCode);
        return new ResponseEntity<>(mobilePhoneCode, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MobilePhoneCode mobilePhoneCode = phoneMobileCodeRepository.findOne(id);
        if (mobilePhoneCode == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MobilePhoneCode with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            phoneMobileCodeRepository.delete(mobilePhoneCode);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
