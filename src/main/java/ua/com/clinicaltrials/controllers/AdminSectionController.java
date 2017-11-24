package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Section;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.SectionRepositoty;

@RestController
@RequestMapping("/admin/section")
public class AdminSectionController {

    @Autowired
    SectionRepositoty sectionRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Section section){
        Section currentSection = sectionRepository.findOne(id);
        if (currentSection == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Section with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        sectionRepository.save(currentSection);
        return new ResponseEntity<>(currentSection, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Section section){

        if (section == null){
            return new ResponseEntity<>(new CustomErrorType("No section"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (section.getNameUa() == null ||
                section.getNameEn() == null ||
                section.getNameRu() == null ||
                section.getNameUa().isEmpty() ||
                section.getNameEn().isEmpty() ||
                section.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No section name"),HttpStatus.NOT_ACCEPTABLE);
        }

        sectionRepository.save(section);
        return new ResponseEntity<>(section, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Section section = sectionRepository.findOne(id);
        if (section == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Section with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            sectionRepository.delete(section);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
