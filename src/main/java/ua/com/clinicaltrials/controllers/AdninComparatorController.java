package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Comparator;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ComparatorRepository;

@RestController
@RequestMapping("/admin/")
public class AdninComparatorController {
    @Autowired
    ComparatorRepository comparatorRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Comparator comparator){
        Comparator currentComparator = comparatorRepository.findOne(id);
        if (currentComparator == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Comparator with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        comparatorRepository.save(currentComparator);
        return new ResponseEntity<>(currentComparator, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Comparator comparator){

        if (comparator == null){
            return new ResponseEntity<>(new CustomErrorType("No comparator"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (comparator.getNameUa() == null ||
                comparator.getNameEn() == null ||
                comparator.getNameRu() == null ||
                comparator.getNameUa().isEmpty() ||
                comparator.getNameEn().isEmpty() ||
                comparator.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No comparator name"),HttpStatus.NOT_ACCEPTABLE);
        }

        comparatorRepository.save(comparator);
        return new ResponseEntity<>(comparator, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Comparator comparator = comparatorRepository.findOne(id);
        if (comparator == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Comparator with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            comparatorRepository.delete(comparator);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
