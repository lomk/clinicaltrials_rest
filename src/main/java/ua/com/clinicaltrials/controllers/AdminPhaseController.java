package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Phase;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.PhaseRepository;

@RestController
@RequestMapping("/admin/phase")
public class AdminPhaseController {
    @Autowired
    PhaseRepository phaseRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Phase phase){
        Phase currentPhase = phaseRepository.findOne(id);
        if (currentPhase == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Phase with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        phaseRepository.save(currentPhase);
        return new ResponseEntity<>(currentPhase, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Phase phase){

        if (phase == null){
            return new ResponseEntity<>(new CustomErrorType("No phase"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (phase.getNameUa() == null ||
                phase.getNameEn() == null ||
                phase.getNameRu() == null ||
                phase.getNameUa().isEmpty() ||
                phase.getNameEn().isEmpty() ||
                phase.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No phase name"),HttpStatus.NOT_ACCEPTABLE);
        }

        phaseRepository.save(phase);
        return new ResponseEntity<>(phase, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Phase phase = phaseRepository.findOne(id);
        if (phase == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Phase with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            phaseRepository.delete(phase);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
