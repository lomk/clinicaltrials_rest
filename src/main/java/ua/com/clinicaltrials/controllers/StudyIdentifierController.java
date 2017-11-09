package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.StudyIdentifier;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyIdentifierRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StudyIdentifierController {
    @Autowired
    StudyIdentifierRepository studyIdentifierRepository;
    
    @RequestMapping(value = "study_identifier", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "search", required = false) Optional<String> search,
            @RequestParam(value = "all", required = false) Optional<Boolean> all,
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "lang", required = false) Optional<String> lang
    ) {

        if (search.isPresent() && page.isPresent() && lang.isPresent() && !id.isPresent() && !all.isPresent()) {
            if (lang.get().equals("ru")){
                List<StudyIdentifier> studyIdentifierList = studyIdentifierRepository.findAllByNameRuContains(search.get());
                if (studyIdentifierList == null){
                    return new ResponseEntity(new CustomErrorType("No data found"),
                            HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(studyIdentifierList, HttpStatus.OK);
            }
            if (lang.get().equals("ua")){
                List<StudyIdentifier> studyIdentifierList = studyIdentifierRepository.findAllByNameUaContains(search.get());
                if (studyIdentifierList == null){
                    return new ResponseEntity(new CustomErrorType("No data found"),
                            HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(studyIdentifierList, HttpStatus.OK);
            }
            if (lang.get().equals("en")){
                List<StudyIdentifier> studyIdentifierList = studyIdentifierRepository.findAllByNameEnContains(search.get());
                if (studyIdentifierList == null){
                    return new ResponseEntity(new CustomErrorType("No data found"),
                            HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(studyIdentifierList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Bad lang", HttpStatus.OK);
        }

        if (all.isPresent() && all.get() == true && page.isPresent() && !id.isPresent() && !search.isPresent()) {

            List<StudyIdentifier> studyIdentifierList = studyIdentifierRepository.findAll();
            if (studyIdentifierList == null){
                return new ResponseEntity(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studyIdentifierList, HttpStatus.OK);
        }

        if (id.isPresent() && !id.get().toString().isEmpty() && !page.isPresent() && !all.isPresent() && !search.isPresent()) {
            System.out.println(id.get());
            StudyIdentifier studyIdentifier = studyIdentifierRepository.findOne(id.get());
            if (studyIdentifier == null){
                return new ResponseEntity(new CustomErrorType(
                        "StudyIdentifier with id " + id.get() + " not found."),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studyIdentifier, HttpStatus.OK);
        }

        return new ResponseEntity(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
