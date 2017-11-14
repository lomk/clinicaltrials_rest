package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.RecruitmentStatus;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.RecruitmentStatusRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class RecruitmentStatusController {
    @Autowired
    RecruitmentStatusRepository recruitmentStatusRepository;

    @RequestMapping(value = "recruitmentStatus", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page
    ) {

        if (page.isPresent() && !id.isPresent()) {

            List<RecruitmentStatus> recruitmentStatusList = recruitmentStatusRepository.findAll();
            if (recruitmentStatusList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(recruitmentStatusList, HttpStatus.OK);
        }

        if (id.isPresent() && !id.get().toString().isEmpty() && !page.isPresent()) {

            RecruitmentStatus recruitmentStatus = recruitmentStatusRepository.findOne(id.get());
            if (recruitmentStatus == null){
                try {
                    return new ResponseEntity<>(new CustomErrorType(
                            "RecruitmentStatus with id " + id.get() + " not found."),
                            HttpStatus.NOT_FOUND);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(recruitmentStatus, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
