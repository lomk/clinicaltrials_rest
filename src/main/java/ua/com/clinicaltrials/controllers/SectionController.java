package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.Section;
import ua.com.clinicaltrials.repositories.SectionRepositoty;
import ua.com.clinicaltrials.errors.CustomErrorType;

import java.util.List;
import java.util.Optional;

@RestController
public class SectionController {
    @Autowired
    SectionRepositoty sectionRepository;

    @RequestMapping(value = "section", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "menu", required = false) Optional<Integer> menu
    ) {

        if (page.isPresent() && !id.isPresent() && !menu.isPresent()) {

            List<Section> sectionList = sectionRepository.findAll();
            if (sectionList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(sectionList, HttpStatus.OK);
        }

        if (menu.isPresent() && !menu.get().toString().isEmpty() && !page.isPresent() && !id.isPresent()) {
            List<Section> sectionList = sectionRepository.findByMenu(menu.get());
            if (sectionList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(sectionList, HttpStatus.OK);
        }

        if (id.isPresent() && !id.get().toString().isEmpty() && !page.isPresent() && !menu.isPresent()) {

            Section section = sectionRepository.findOne(id.get());
            if (section == null){
                try {
                    return new ResponseEntity<>(new CustomErrorType(
                            "Section with id " + id.get() + " not found."),
                            HttpStatus.NOT_FOUND);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(section, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
