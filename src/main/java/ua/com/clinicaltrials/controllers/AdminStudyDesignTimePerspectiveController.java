package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignTimePerspective;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignTimePerspectiveRepository;

@RestController
@RequestMapping("/admin/study-design-time-perspective")
public class AdminStudyDesignTimePerspectiveController {
    @Autowired
    StudyDesignTimePerspectiveRepository studyDesignTimePerspectiveRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignTimePerspective studyDesignTimePerspective){
        StudyDesignTimePerspective currentStudyDesignTimePerspective = studyDesignTimePerspectiveRepository.findOne(id);
        if (currentStudyDesignTimePerspective == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignTimePerspective with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyDesignTimePerspectiveRepository.save(currentStudyDesignTimePerspective);
        return new ResponseEntity<>(currentStudyDesignTimePerspective, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignTimePerspective studyDesignTimePerspective){

        if (studyDesignTimePerspective == null){
            return new ResponseEntity<>(new CustomErrorType("No studyDesignTimePerspective"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyDesignTimePerspective.getNameUa() == null ||
                studyDesignTimePerspective.getNameEn() == null ||
                studyDesignTimePerspective.getNameRu() == null ||
                studyDesignTimePerspective.getNameUa().isEmpty() ||
                studyDesignTimePerspective.getNameEn().isEmpty() ||
                studyDesignTimePerspective.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyDesignTimePerspective name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyDesignTimePerspectiveRepository.save(studyDesignTimePerspective);
        return new ResponseEntity<>(studyDesignTimePerspective, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyDesignTimePerspective studyDesignTimePerspective = studyDesignTimePerspectiveRepository.findOne(id);
        if (studyDesignTimePerspective == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignTimePerspective with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyDesignTimePerspectiveRepository.delete(studyDesignTimePerspective);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
