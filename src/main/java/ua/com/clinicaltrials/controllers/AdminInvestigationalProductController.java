package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.InvestigationalProduct;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.InvestigationalProductRepository;

@RestController
@RequestMapping("/admin/")
public class AdminInvestigationalProductController {
    @Autowired
    InvestigationalProductRepository investigationalProductRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody InvestigationalProduct investigationalProduct){
        InvestigationalProduct currentInvestigationalProduct = investigationalProductRepository.findOne(id);
        if (currentInvestigationalProduct == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. InvestigationalProduct with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        investigationalProductRepository.save(currentInvestigationalProduct);
        return new ResponseEntity<>(currentInvestigationalProduct, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody InvestigationalProduct investigationalProduct){

        if (investigationalProduct == null){
            return new ResponseEntity<>(new CustomErrorType("No investigationalProduct"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (investigationalProduct.getNameUa() == null ||
                investigationalProduct.getNameEn() == null ||
                investigationalProduct.getNameRu() == null ||
                investigationalProduct.getNameUa().isEmpty() ||
                investigationalProduct.getNameEn().isEmpty() ||
                investigationalProduct.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No investigationalProduct name"),HttpStatus.NOT_ACCEPTABLE);
        }

        investigationalProductRepository.save(investigationalProduct);
        return new ResponseEntity<>(investigationalProduct, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        InvestigationalProduct investigationalProduct = investigationalProductRepository.findOne(id);
        if (investigationalProduct == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "InvestigationalProduct with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            investigationalProductRepository.delete(investigationalProduct);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
