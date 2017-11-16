package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.AcademicDegree;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.AcademicDegreeRepository;

@RestController
@RequestMapping("/admin/")
public class AdminAcademicDegreeController {

    @Autowired
    AcademicDegreeRepository academicDegreeRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AcademicDegree academicDegree){
        AcademicDegree currentAcademicDegree = academicDegreeRepository.findOne(id);
        if (currentAcademicDegree == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. AcademicDegree with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
       
        academicDegreeRepository.save(currentAcademicDegree);
        return new ResponseEntity<>(currentAcademicDegree, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody AcademicDegree academicDegree){

        if (academicDegree == null){
            return new ResponseEntity<>(new CustomErrorType("No academicDegree"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (academicDegree.getNameUa() == null ||
            academicDegree.getNameEn() == null ||
            academicDegree.getNameRu() == null ||
            academicDegree.getNameUa().isEmpty() ||
            academicDegree.getNameEn().isEmpty() ||
            academicDegree.getNameRu().isEmpty()
        ) {
            return new ResponseEntity(new CustomErrorType("No academicDegree name"),HttpStatus.NOT_ACCEPTABLE);
        }

        academicDegreeRepository.save(academicDegree);
        return new ResponseEntity<>(academicDegree, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        AcademicDegree academicDegree = academicDegreeRepository.findOne(id);
        if (academicDegree == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "AcademicDegree with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            academicDegreeRepository.delete(academicDegree);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
