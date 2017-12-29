package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignPrimaryPurpose;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignPrimaryPurposeRepository;

@RestController
@RequestMapping("/admin/study-design-primary-purpose")
public class AdminStudyDesignPrimaryPurposeController {
    @Autowired
    StudyDesignPrimaryPurposeRepository studyDesignPrimaryPurposeRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignPrimaryPurpose studyDesignPrimaryPurpose){
        StudyDesignPrimaryPurpose currentStudyDesignPrimaryPurpose = studyDesignPrimaryPurposeRepository.findOne(id);
        if (currentStudyDesignPrimaryPurpose == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignPrimaryPurpose with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyDesignPrimaryPurposeRepository.save(currentStudyDesignPrimaryPurpose);
        return new ResponseEntity<>(currentStudyDesignPrimaryPurpose, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignPrimaryPurpose studyDesignPrimaryPurpose){

        if (studyDesignPrimaryPurpose == null){
            return new ResponseEntity<>(new CustomErrorType("No studyDesignPrimaryPurpose"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyDesignPrimaryPurpose.getNameUa() == null ||
                studyDesignPrimaryPurpose.getNameEn() == null ||
                studyDesignPrimaryPurpose.getNameRu() == null ||
                studyDesignPrimaryPurpose.getNameUa().isEmpty() ||
                studyDesignPrimaryPurpose.getNameEn().isEmpty() ||
                studyDesignPrimaryPurpose.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyDesignPrimaryPurpose name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyDesignPrimaryPurposeRepository.save(studyDesignPrimaryPurpose);
        return new ResponseEntity<>(studyDesignPrimaryPurpose, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyDesignPrimaryPurpose studyDesignPrimaryPurpose = studyDesignPrimaryPurposeRepository.findOne(id);
        if (studyDesignPrimaryPurpose == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignPrimaryPurpose with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyDesignPrimaryPurposeRepository.delete(studyDesignPrimaryPurpose);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
