package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Sponsor;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.SponsorRepository;

@RestController
@RequestMapping("/admin/sponsor")
public class AdminSponsorController {
    @Autowired
    SponsorRepository sponsorRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Sponsor sponsor){
        Sponsor currentSponsor = sponsorRepository.findOne(id);
        if (currentSponsor == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Sponsor with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        sponsorRepository.save(currentSponsor);
        return new ResponseEntity<>(currentSponsor, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Sponsor sponsor){

        if (sponsor == null){
            return new ResponseEntity<>(new CustomErrorType("No sponsor"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (sponsor.getNameUa() == null ||
                sponsor.getNameEn() == null ||
                sponsor.getNameRu() == null ||
                sponsor.getNameUa().isEmpty() ||
                sponsor.getNameEn().isEmpty() ||
                sponsor.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No sponsor name"),HttpStatus.NOT_ACCEPTABLE);
        }

        sponsorRepository.save(sponsor);
        return new ResponseEntity<>(sponsor, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Sponsor sponsor = sponsorRepository.findOne(id);
        if (sponsor == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Sponsor with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            sponsorRepository.delete(sponsor);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
