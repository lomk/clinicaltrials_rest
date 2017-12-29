package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyStatus;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyStatusRepository;

@RestController
@RequestMapping("/admin/study-status")
public class AdminStudyStatusController {
    @Autowired
    StudyStatusRepository studyStatusRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyStatus studyStatus){
        StudyStatus currentStudyStatus = studyStatusRepository.findOne(id);
        if (currentStudyStatus == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyStatus with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyStatusRepository.save(currentStudyStatus);
        return new ResponseEntity<>(currentStudyStatus, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyStatus studyStatus){

        if (studyStatus == null){
            return new ResponseEntity<>(new CustomErrorType("No studyStatus"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyStatusRepository.save(studyStatus);
        return new ResponseEntity<>(studyStatus, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyStatus studyStatus = studyStatusRepository.findOne(id);
        if (studyStatus == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyStatus with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyStatusRepository.delete(studyStatus);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
