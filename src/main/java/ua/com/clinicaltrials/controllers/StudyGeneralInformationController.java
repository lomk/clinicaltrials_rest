package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.StudyGeneralInformation;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.StudyGeneralInformationRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StudyGeneralInformationController {
    @Autowired
    StudyGeneralInformationRepository studyGeneralInformationRepository;

    @RequestMapping(value = "studyGeneralInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page
    ) {

        if (page.isPresent() && !id.isPresent()) {

            List<StudyGeneralInformation> studyGeneralInformationList = studyGeneralInformationRepository.findAll();
            if (studyGeneralInformationList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(studyGeneralInformationList, HttpStatus.OK);
        }

        if (id.isPresent() && !id.get().toString().isEmpty() && !page.isPresent()) {

            StudyGeneralInformation studyGeneralInformation = studyGeneralInformationRepository.findOne(id.get());
            if (studyGeneralInformation == null){
                try {
                    return new ResponseEntity<>(new CustomErrorType(
                            "StudyGeneralInformation with id " + id.get() + " not found."),
                            HttpStatus.NOT_FOUND);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(studyGeneralInformation, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
