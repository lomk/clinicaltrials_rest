package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyDesignObservationalModel;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyDesignObservationalModelRepository;

@RestController
@RequestMapping("/admin/study-design-obdervational-model")
public class AdminStudyDesignObservationalModelController {
    @Autowired
    StudyDesignObservationalModelRepository studyDesignObservationalModelRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyDesignObservationalModel studyDesignObservationalModel){
        StudyDesignObservationalModel currentStudyDesignObservationalModel = studyDesignObservationalModelRepository.findOne(id);
        if (currentStudyDesignObservationalModel == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyDesignObservationalModel with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyDesignObservationalModelRepository.save(currentStudyDesignObservationalModel);
        return new ResponseEntity<>(currentStudyDesignObservationalModel, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyDesignObservationalModel studyDesignObservationalModel){

        if (studyDesignObservationalModel == null){
            return new ResponseEntity<>(new CustomErrorType("No studyDesignObservationalModel"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyDesignObservationalModel.getNameUa() == null ||
                studyDesignObservationalModel.getNameEn() == null ||
                studyDesignObservationalModel.getNameRu() == null ||
                studyDesignObservationalModel.getNameUa().isEmpty() ||
                studyDesignObservationalModel.getNameEn().isEmpty() ||
                studyDesignObservationalModel.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyDesignObservationalModel name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyDesignObservationalModelRepository.save(studyDesignObservationalModel);
        return new ResponseEntity<>(studyDesignObservationalModel, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyDesignObservationalModel studyDesignObservationalModel = studyDesignObservationalModelRepository.findOne(id);
        if (studyDesignObservationalModel == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyDesignObservationalModel with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyDesignObservationalModelRepository.delete(studyDesignObservationalModel);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
