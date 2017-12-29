package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.StudyRelatedMaterial;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyRelatedMaterialRepository;

@RestController
@RequestMapping("/admin/study-related-material")
public class AdminStudyRelatedMaterialController {
    @Autowired
    StudyRelatedMaterialRepository studyRelatedMaterialRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody StudyRelatedMaterial studyRelatedMaterial){
        StudyRelatedMaterial currentStudyRelatedMaterial = studyRelatedMaterialRepository.findOne(id);
        if (currentStudyRelatedMaterial == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. StudyRelatedMaterial with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        studyRelatedMaterialRepository.save(currentStudyRelatedMaterial);
        return new ResponseEntity<>(currentStudyRelatedMaterial, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody StudyRelatedMaterial studyRelatedMaterial){

        if (studyRelatedMaterial == null){
            return new ResponseEntity<>(new CustomErrorType("No studyRelatedMaterial"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (studyRelatedMaterial.getNameUa() == null ||
                studyRelatedMaterial.getNameEn() == null ||
                studyRelatedMaterial.getNameRu() == null ||
                studyRelatedMaterial.getNameUa().isEmpty() ||
                studyRelatedMaterial.getNameEn().isEmpty() ||
                studyRelatedMaterial.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No studyRelatedMaterial name"),HttpStatus.NOT_ACCEPTABLE);
        }

        studyRelatedMaterialRepository.save(studyRelatedMaterial);
        return new ResponseEntity<>(studyRelatedMaterial, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        StudyRelatedMaterial studyRelatedMaterial = studyRelatedMaterialRepository.findOne(id);
        if (studyRelatedMaterial == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "StudyRelatedMaterial with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            studyRelatedMaterialRepository.delete(studyRelatedMaterial);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
