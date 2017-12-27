package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MedicalInstitutionDepartment;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MedicalInstitutionDepartmentRepository;

@RestController
@RequestMapping("/admin/medical-institution-department")
public class AdminMedicalInstitutionDepartmentController {
    @Autowired
    MedicalInstitutionDepartmentRepository medicalInstitutionDepartmentRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedicalInstitutionDepartment medicalInstitutionDepartment){
        MedicalInstitutionDepartment currentMedicalInstitutionDepartment = medicalInstitutionDepartmentRepository.findOne(id);
        if (currentMedicalInstitutionDepartment == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MedicalInstitutionDepartment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        medicalInstitutionDepartmentRepository.save(currentMedicalInstitutionDepartment);
        return new ResponseEntity<>(currentMedicalInstitutionDepartment, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MedicalInstitutionDepartment medicalInstitutionDepartment){

        if (medicalInstitutionDepartment == null){
            return new ResponseEntity<>(new CustomErrorType("No medicalInstitutionDepartment"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (medicalInstitutionDepartment.getNameUa() == null ||
                medicalInstitutionDepartment.getNameEn() == null ||
                medicalInstitutionDepartment.getNameRu() == null ||
                medicalInstitutionDepartment.getNameUa().isEmpty() ||
                medicalInstitutionDepartment.getNameEn().isEmpty() ||
                medicalInstitutionDepartment.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No medicalInstitutionDepartment name"),HttpStatus.NOT_ACCEPTABLE);
        }

        medicalInstitutionDepartmentRepository.save(medicalInstitutionDepartment);
        return new ResponseEntity<>(medicalInstitutionDepartment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MedicalInstitutionDepartment medicalInstitutionDepartment = medicalInstitutionDepartmentRepository.findOne(id);
        if (medicalInstitutionDepartment == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MedicalInstitutionDepartment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            medicalInstitutionDepartmentRepository.delete(medicalInstitutionDepartment);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
