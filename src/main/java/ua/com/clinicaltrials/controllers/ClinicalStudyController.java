package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.*;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "studies")
public class ClinicalStudyController {

    @Autowired
    ClinicalStudyRepository clinicalStudyRepository;
    @Autowired
    StudyIdentifierRepository studyIdentifierRepository;
    @Autowired
    StudyGeneralInformationRepository studyGeneralInformationRepository;
    @Autowired
    EligibilityRepository eligibilityRepository;
    @Autowired
    TrialSiteRepository trialSiteRepository;
    @Autowired
    CROUkraineRepository croUkraineRepository;
    @Autowired
    SponsorRepository sponsorRepository;
    
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Optional<Integer> page) {

        if (page.isPresent()) {
            Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "creationDate"));
            Pageable pageable = new PageRequest(page.get(), 9, sort);
            Page<ClinicalStudy> clinicalStudyPage = clinicalStudyRepository.findAll(pageable);
            List<ClinicalStudy> clinicalStudyList = clinicalStudyPage.getContent();

            if (clinicalStudyList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clinicalStudyList, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "one/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id) {

            ClinicalStudy clinicalStudy = clinicalStudyRepository.findOne(id);
            if (clinicalStudy == null) {
                return new ResponseEntity<>(new CustomErrorType(
                        "ClinicalStudy with id " + id + " not found."),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value = "studyIdentifier/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByStudyIdentifier(@PathVariable("id") Integer id) {

        StudyIdentifier currentStudyIdentifier = studyIdentifierRepository.findOne(id);
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findByStudyIdentifier(currentStudyIdentifier);
        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with studyIdentifier id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value = "studyGeneralInformation/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByStudyGeneralInformation(@PathVariable("id") Integer id) {

        StudyGeneralInformation currentStudyGeneralInformation= studyGeneralInformationRepository.findOne(id);
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findByStudyGeneralInformation(currentStudyGeneralInformation);
        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with studyGeneralInformation id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value = "eligibility/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByStudyEligibility(@PathVariable("id") Integer id) {

        Eligibility currentEligibility = eligibilityRepository.findOne(id);
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findByEligibility(currentEligibility);
        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with eligibility id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value = "trialSite/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByTrialSite(@PathVariable("id") Integer id) {

        TrialSite currentTrialSite = trialSiteRepository.findOne(id);
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findByTrialSite(currentTrialSite);
        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with trialSite id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value = "croUkraine/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByCROUkraine(@PathVariable("id") Integer id) {

        CROUkraine currentCROUkraine = croUkraineRepository.findOne(id);
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findByCroUkraine(currentCROUkraine);
        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with croUkraine id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }

    @RequestMapping(value = "sponsor/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBySponsor(@PathVariable("id") Integer id) {

        Sponsor currentSponsor = sponsorRepository.findOne(id);
        ClinicalStudy clinicalStudy = clinicalStudyRepository.findBySponsor(currentSponsor);
        if (clinicalStudy == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "ClinicalStudy with sponsor id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clinicalStudy, HttpStatus.OK);
    }
}
