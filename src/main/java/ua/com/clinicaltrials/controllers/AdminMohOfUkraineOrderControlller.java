package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MohOfUkraineOrder;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MohOfUkraineOrderRepository;

@RestController
@RequestMapping("/admin/moh-of-ukraine-order")
public class AdminMohOfUkraineOrderControlller {
    @Autowired
    MohOfUkraineOrderRepository mohOfUkraineOrderRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MohOfUkraineOrder mohOfUkraineOrder){
        MohOfUkraineOrder currentMohOfUkraineOrder = mohOfUkraineOrderRepository.findOne(id);
        if (currentMohOfUkraineOrder == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MohOfUkraineOrder with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        mohOfUkraineOrderRepository.save(currentMohOfUkraineOrder);
        return new ResponseEntity<>(currentMohOfUkraineOrder, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MohOfUkraineOrder mohOfUkraineOrder){

        if (mohOfUkraineOrder == null){
            return new ResponseEntity<>(new CustomErrorType("No mohOfUkraineOrder"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (mohOfUkraineOrder.getNameUa() == null ||
                mohOfUkraineOrder.getNameEn() == null ||
                mohOfUkraineOrder.getNameRu() == null ||
                mohOfUkraineOrder.getNameUa().isEmpty() ||
                mohOfUkraineOrder.getNameEn().isEmpty() ||
                mohOfUkraineOrder.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No mohOfUkraineOrder name"),HttpStatus.NOT_ACCEPTABLE);
        }

        mohOfUkraineOrderRepository.save(mohOfUkraineOrder);
        return new ResponseEntity<>(mohOfUkraineOrder, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MohOfUkraineOrder mohOfUkraineOrder = mohOfUkraineOrderRepository.findOne(id);
        if (mohOfUkraineOrder == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MohOfUkraineOrder with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            mohOfUkraineOrderRepository.delete(mohOfUkraineOrder);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
