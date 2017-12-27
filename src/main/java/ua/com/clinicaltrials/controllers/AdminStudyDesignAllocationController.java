package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignAllocation;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignAllocationRepository;

@RestController
@RequestMapping("/admin/study-design-allocation-controller")
public class AdminStudyDesignAllocationController {
    @Autowired
    StudyDesignAllocationRepository studyDesignAllocationRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignAllocation studyDesignAllocation){
        StudyDesignAllocation currentStudyDesignAllocation = studyDesignAllocationRepository.findOne(id);
        if (currentStudyDesignAllocation == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignAllocation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyDesignAllocationRepository.save(currentStudyDesignAllocation);
        return new ResponseEntity<>(currentStudyDesignAllocation, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignAllocation studyDesignAllocation){

        if (studyDesignAllocation == null){
            return new ResponseEntity<>(new CustomErrorType("No studyDesignAllocation"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyDesignAllocation.getNameUa() == null ||
                studyDesignAllocation.getNameEn() == null ||
                studyDesignAllocation.getNameRu() == null ||
                studyDesignAllocation.getNameUa().isEmpty() ||
                studyDesignAllocation.getNameEn().isEmpty() ||
                studyDesignAllocation.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyDesignAllocation name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyDesignAllocationRepository.save(studyDesignAllocation);
        return new ResponseEntity<>(studyDesignAllocation, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyDesignAllocation studyDesignAllocation = studyDesignAllocationRepository.findOne(id);
        if (studyDesignAllocation == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignAllocation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyDesignAllocationRepository.delete(studyDesignAllocation);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
