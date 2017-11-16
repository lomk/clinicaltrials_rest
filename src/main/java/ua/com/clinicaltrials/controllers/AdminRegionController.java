package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Region;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.RegionRepository;

@RestController
@RequestMapping("/admin/")
public class AdminRegionController {
    @Autowired
    RegionRepository regionRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Region region){
        Region currentRegion = regionRepository.findOne(id);
        if (currentRegion == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Region with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        regionRepository.save(currentRegion);
        return new ResponseEntity<>(currentRegion, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Region region){

        if (region == null){
            return new ResponseEntity<>(new CustomErrorType("No region"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (region.getNameUa() == null ||
                region.getNameEn() == null ||
                region.getNameRu() == null ||
                region.getNameUa().isEmpty() ||
                region.getNameEn().isEmpty() ||
                region.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No region name"),HttpStatus.NOT_ACCEPTABLE);
        }

        regionRepository.save(region);
        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Region region = regionRepository.findOne(id);
        if (region == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Region with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            regionRepository.delete(region);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
