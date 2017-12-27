package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.MedicalInstitutionOfHigherEducationDepartment;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MedicalInstitutionOfHigherEducationDepartmentRepository;

@RestController
@RequestMapping("/admin/medical-institution-of-higher-education-department")
public class AdminMedicalInstitutionOfHigherEducationDepartmentController {
    @Autowired
    MedicalInstitutionOfHigherEducationDepartmentRepository medicalInstitutionOfHigherEducationDepartmentRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedicalInstitutionOfHigherEducationDepartment medicalInstitutionOfHigherEducationDepartment){
        MedicalInstitutionOfHigherEducationDepartment currentMedicalInstitutionOfHigherEducationDepartment = medicalInstitutionOfHigherEducationDepartmentRepository.findOne(id);
        if (currentMedicalInstitutionOfHigherEducationDepartment == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. MedicalInstitutionOfHigherEducationDepartment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        medicalInstitutionOfHigherEducationDepartmentRepository.save(currentMedicalInstitutionOfHigherEducationDepartment);
        return new ResponseEntity<>(currentMedicalInstitutionOfHigherEducationDepartment, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody MedicalInstitutionOfHigherEducationDepartment medicalInstitutionOfHigherEducationDepartment){

        if (medicalInstitutionOfHigherEducationDepartment == null){
            return new ResponseEntity<>(new CustomErrorType("No medicalInstitutionOfHigherEducationDepartment"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (medicalInstitutionOfHigherEducationDepartment.getNameUa() == null ||
                medicalInstitutionOfHigherEducationDepartment.getNameEn() == null ||
                medicalInstitutionOfHigherEducationDepartment.getNameRu() == null ||
                medicalInstitutionOfHigherEducationDepartment.getNameUa().isEmpty() ||
                medicalInstitutionOfHigherEducationDepartment.getNameEn().isEmpty() ||
                medicalInstitutionOfHigherEducationDepartment.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No medicalInstitutionOfHigherEducationDepartment name"),HttpStatus.NOT_ACCEPTABLE);
        }

        medicalInstitutionOfHigherEducationDepartmentRepository.save(medicalInstitutionOfHigherEducationDepartment);
        return new ResponseEntity<>(medicalInstitutionOfHigherEducationDepartment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        MedicalInstitutionOfHigherEducationDepartment medicalInstitutionOfHigherEducationDepartment = medicalInstitutionOfHigherEducationDepartmentRepository.findOne(id);
        if (medicalInstitutionOfHigherEducationDepartment == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "MedicalInstitutionOfHigherEducationDepartment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            medicalInstitutionOfHigherEducationDepartmentRepository.delete(medicalInstitutionOfHigherEducationDepartment);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
