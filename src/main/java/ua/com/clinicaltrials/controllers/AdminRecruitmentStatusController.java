package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.RecruitmentStatus;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.RecruitmentStatusRepository;

@RestController
@RequestMapping("/admin/")
public class AdminRecruitmentStatusController {
    @Autowired
    RecruitmentStatusRepository recruitmentStatusRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody RecruitmentStatus recruitmentStatus){
        RecruitmentStatus currentRecruitmentStatus = recruitmentStatusRepository.findOne(id);
        if (currentRecruitmentStatus == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. RecruitmentStatus with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        recruitmentStatusRepository.save(currentRecruitmentStatus);
        return new ResponseEntity<>(currentRecruitmentStatus, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody RecruitmentStatus recruitmentStatus){

        if (recruitmentStatus == null){
            return new ResponseEntity<>(new CustomErrorType("No recruitmentStatus"),HttpStatus.NOT_ACCEPTABLE);
        }


        recruitmentStatusRepository.save(recruitmentStatus);
        return new ResponseEntity<>(recruitmentStatus, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        RecruitmentStatus recruitmentStatus = recruitmentStatusRepository.findOne(id);
        if (recruitmentStatus == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "RecruitmentStatus with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            recruitmentStatusRepository.delete(recruitmentStatus);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
