package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignInterventionModel;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignInterventionModelRepository;

@RestController
@RequestMapping("/admin/")
public class AdminStudyDesignInterventionModelController {
    @Autowired
    StudyDesignInterventionModelRepository studyDesignInterventionModelRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignInterventionModel studyDesignInterventionModel){
        StudyDesignInterventionModel currentStudyDesignInterventionModel = studyDesignInterventionModelRepository.findOne(id);
        if (currentStudyDesignInterventionModel == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignInterventionModel with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyDesignInterventionModelRepository.save(currentStudyDesignInterventionModel);
        return new ResponseEntity<>(currentStudyDesignInterventionModel, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignInterventionModel studyDesignInterventionModel){

        if (studyDesignInterventionModel == null){
            return new ResponseEntity<>(new CustomErrorType("No studyDesignInterventionModel"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyDesignInterventionModel.getNameUa() == null ||
                studyDesignInterventionModel.getNameEn() == null ||
                studyDesignInterventionModel.getNameRu() == null ||
                studyDesignInterventionModel.getNameUa().isEmpty() ||
                studyDesignInterventionModel.getNameEn().isEmpty() ||
                studyDesignInterventionModel.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyDesignInterventionModel name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyDesignInterventionModelRepository.save(studyDesignInterventionModel);
        return new ResponseEntity<>(studyDesignInterventionModel, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyDesignInterventionModel studyDesignInterventionModel = studyDesignInterventionModelRepository.findOne(id);
        if (studyDesignInterventionModel == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignInterventionModel with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyDesignInterventionModelRepository.delete(studyDesignInterventionModel);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
