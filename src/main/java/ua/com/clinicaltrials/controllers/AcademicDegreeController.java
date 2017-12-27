package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.AcademicDegree;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.AcademicDegreeRepository;

import java.util.List;
import java.util.Optional;


@RestController
public class AcademicDegreeController {

    @Autowired
    AcademicDegreeRepository academicDegreeRepository;

    @RequestMapping(value = "academic-degree", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<String> page
    ) {

        if (page.isPresent() && page.get().contains("all") && !id.isPresent()) {

            List<AcademicDegree> academicDegreeList = academicDegreeRepository.findAll();
            if (academicDegreeList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(academicDegreeList, HttpStatus.OK);
        }

        if (id.isPresent() && !id.get().toString().isEmpty() && !page.isPresent()) {

            AcademicDegree academicDegree = academicDegreeRepository.findOne(id.get());
            if (academicDegree == null){
                try {
                    return new ResponseEntity<>(new CustomErrorType(
                            "AcademicDegree with id " + id.get() + " not found."),
                            HttpStatus.NOT_FOUND);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(academicDegree, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
