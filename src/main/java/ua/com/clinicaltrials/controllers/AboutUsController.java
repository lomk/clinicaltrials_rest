package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.AboutUsArticle;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.AboutUsArticleRepository;

import java.util.List;

@RestController
@RequestMapping("/about-us")
public class AboutUsController {
    @Autowired
    AboutUsArticleRepository aboutUsArticleRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> listAboutUsArticle() {
        List<AboutUsArticle> aboutUsArticleList = aboutUsArticleRepository.findAll();
        if (aboutUsArticleList == null){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aboutUsArticleList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAboutUsArticle(@PathVariable("id") Integer id) {
        AboutUsArticle aboutUsArticle = aboutUsArticleRepository.findOne(id);
        if (aboutUsArticle == null){
            return new ResponseEntity(new CustomErrorType(
                    "AboutUsArticle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aboutUsArticle, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAboutUsArticle(@PathVariable Integer id, @RequestBody AboutUsArticle aboutUsArticle){
        AboutUsArticle currentAboutUsArticle = aboutUsArticleRepository.findOne(id);
        if (currentAboutUsArticle == null){
            return new ResponseEntity(new CustomErrorType(
                    "Unable to upate. AboutUsArticle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        aboutUsArticleRepository.save(currentAboutUsArticle);
        return new ResponseEntity<>(currentAboutUsArticle, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addAboutUsArticle(@RequestBody AboutUsArticle aboutUsArticle){


        aboutUsArticleRepository.save(aboutUsArticle);
        return new ResponseEntity<>(aboutUsArticle, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delAboutUsArticle(@PathVariable("id") Integer id) {
        AboutUsArticle aboutUsArticle = aboutUsArticleRepository.findOne(id);
        if (aboutUsArticle == null ){
            return new ResponseEntity(new CustomErrorType(
                    "AboutUsArticle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            aboutUsArticleRepository.delete(aboutUsArticle);
        } catch (Exception e){
            return new ResponseEntity(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
