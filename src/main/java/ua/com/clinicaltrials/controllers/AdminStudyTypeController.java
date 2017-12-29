package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyType;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyTypeRepository;

@RestController
@RequestMapping("/admin/study-type")
public class AdminStudyTypeController {
    @Autowired
    StudyTypeRepository studyTypeRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyType studyType){
        StudyType currentStudyType = studyTypeRepository.findOne(id);
        if (currentStudyType == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyType with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyTypeRepository.save(currentStudyType);
        return new ResponseEntity<>(currentStudyType, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyType studyType){

        if (studyType == null){
            return new ResponseEntity<>(new CustomErrorType("No studyType"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyType.getNameUa() == null ||
                studyType.getNameEn() == null ||
                studyType.getNameRu() == null ||
                studyType.getNameUa().isEmpty() ||
                studyType.getNameEn().isEmpty() ||
                studyType.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyType name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyTypeRepository.save(studyType);
        return new ResponseEntity<>(studyType, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyType studyType = studyTypeRepository.findOne(id);
        if (studyType == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyType with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyTypeRepository.delete(studyType);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
