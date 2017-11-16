package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.PhoneMobileCode;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.PhoneMobileCodeRepository;

@RestController
@RequestMapping("/admin/")
public class AdminPhoneMobileCodeController {
    @Autowired
    PhoneMobileCodeRepository phoneMobileCodeRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PhoneMobileCode phoneMobileCode){
        PhoneMobileCode currentPhoneMobileCode = phoneMobileCodeRepository.findOne(id);
        if (currentPhoneMobileCode == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. PhoneMobileCode with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        phoneMobileCodeRepository.save(currentPhoneMobileCode);
        return new ResponseEntity<>(currentPhoneMobileCode, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody PhoneMobileCode phoneMobileCode){

        if (phoneMobileCode == null){
            return new ResponseEntity<>(new CustomErrorType("No phoneMobileCode"),HttpStatus.NOT_ACCEPTABLE);
        }


        phoneMobileCodeRepository.save(phoneMobileCode);
        return new ResponseEntity<>(phoneMobileCode, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        PhoneMobileCode phoneMobileCode = phoneMobileCodeRepository.findOne(id);
        if (phoneMobileCode == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "PhoneMobileCode with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            phoneMobileCodeRepository.delete(phoneMobileCode);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
