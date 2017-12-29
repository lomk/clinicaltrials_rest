package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignMasking;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignMaskingRepository;

@RestController
@RequestMapping("/admin/study-design-masking")
public class AdminStudyDesignMaskingController {
    @Autowired
    StudyDesignMaskingRepository studyDesignMaskingRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignMasking studyDesignMasking){
        StudyDesignMasking currentStudyDesignMasking = studyDesignMaskingRepository.findOne(id);
        if (currentStudyDesignMasking == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignMasking with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyDesignMaskingRepository.save(currentStudyDesignMasking);
        return new ResponseEntity<>(currentStudyDesignMasking, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignMasking studyDesignMasking){

        if (studyDesignMasking == null){
            return new ResponseEntity<>(new CustomErrorType("No studyDesignMasking"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyDesignMasking.getNameUa() == null ||
                studyDesignMasking.getNameEn() == null ||
                studyDesignMasking.getNameRu() == null ||
                studyDesignMasking.getNameUa().isEmpty() ||
                studyDesignMasking.getNameEn().isEmpty() ||
                studyDesignMasking.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyDesignMasking name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyDesignMaskingRepository.save(studyDesignMasking);
        return new ResponseEntity<>(studyDesignMasking, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyDesignMasking studyDesignMasking = studyDesignMaskingRepository.findOne(id);
        if (studyDesignMasking == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignMasking with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyDesignMaskingRepository.delete(studyDesignMasking);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
