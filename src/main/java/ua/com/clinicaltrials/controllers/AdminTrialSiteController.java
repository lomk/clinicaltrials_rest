package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.TrialSite;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.TrialSiteRepository;

@RestController
@RequestMapping("/admin/")
public class AdminTrialSiteController {
    @Autowired
    TrialSiteRepository trialSiteRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody TrialSite trialSite){
        TrialSite currentTrialSite = trialSiteRepository.findOne(id);
        if (currentTrialSite == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. TrialSite with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        trialSiteRepository.save(currentTrialSite);
        return new ResponseEntity<>(currentTrialSite, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody TrialSite trialSite){

        if (trialSite == null){
            return new ResponseEntity<>(new CustomErrorType("No trialSite"),HttpStatus.NOT_ACCEPTABLE);
        }


        trialSiteRepository.save(trialSite);
        return new ResponseEntity<>(trialSite, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        TrialSite trialSite = trialSiteRepository.findOne(id);
        if (trialSite == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "TrialSite with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            trialSiteRepository.delete(trialSite);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
