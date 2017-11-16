package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyGeneralInformation;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyGeneralInformationRepository;

@RestController
@RequestMapping("/admin/")
public class AdminStudyGeneralInformationController {
    @Autowired
    StudyGeneralInformationRepository studyGeneralInformationRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyGeneralInformation studyGeneralInformation){
        StudyGeneralInformation currentStudyGeneralInformation = studyGeneralInformationRepository.findOne(id);
        if (currentStudyGeneralInformation == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyGeneralInformation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyGeneralInformationRepository.save(currentStudyGeneralInformation);
        return new ResponseEntity<>(currentStudyGeneralInformation, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyGeneralInformation studyGeneralInformation){

        if (studyGeneralInformation == null){
            return new ResponseEntity<>(new CustomErrorType("No studyGeneralInformation"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyGeneralInformationRepository.save(studyGeneralInformation);
        return new ResponseEntity<>(studyGeneralInformation, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyGeneralInformation studyGeneralInformation = studyGeneralInformationRepository.findOne(id);
        if (studyGeneralInformation == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyGeneralInformation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyGeneralInformationRepository.delete(studyGeneralInformation);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
