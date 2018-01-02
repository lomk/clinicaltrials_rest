package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.TrialSite;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.TrialSiteRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class TrialSiteController {
    @Autowired
    TrialSiteRepository trialSiteRepository;

    @RequestMapping(value = "trial-site", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page
    ) {

        if (page.isPresent() && !id.isPresent()) {

            Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
            Pageable pageable = new PageRequest(page.get(), 9, sort);
            Page<TrialSite> trialSitePage = trialSiteRepository.findAll(pageable);
            Integer pagesCount = trialSitePage.getTotalPages();
            List<TrialSite> trialSiteList = trialSitePage.getContent();

            if (trialSiteList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(trialSiteList, HttpStatus.OK);
        }

        if (id.isPresent() && !id.get().toString().isEmpty() && !page.isPresent()) {

            TrialSite trialSite = trialSiteRepository.findOne(id.get());
            if (trialSite == null){
                try {
                    return new ResponseEntity<>(new CustomErrorType(
                            "TrialSite with id " + id.get() + " not found."),
                            HttpStatus.NOT_FOUND);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(trialSite, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
