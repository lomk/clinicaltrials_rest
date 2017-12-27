package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.AboutTrialsArticle;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.AboutTrialsArticleRepository;

import java.util.List;

public class AboutTrialsController {
    @Autowired
    AboutTrialsArticleRepository aboutTrialsArticleRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> listAboutTrialsArticle() {
        List<AboutTrialsArticle> aboutTrialsArticleList = aboutTrialsArticleRepository.findAll();
        if (aboutTrialsArticleList == null){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aboutTrialsArticleList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAboutTrialsArticle(@PathVariable("id") Integer id) {
        AboutTrialsArticle aboutTrialsArticle = aboutTrialsArticleRepository.findOne(id);
        if (aboutTrialsArticle == null){
            return new ResponseEntity(new CustomErrorType(
                    "AboutTrialsArticle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aboutTrialsArticle, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAboutTrialsArticle(@PathVariable Integer id, @RequestBody AboutTrialsArticle aboutTrialsArticle){
        AboutTrialsArticle currentAboutTrialsArticle = aboutTrialsArticleRepository.findOne(id);
        if (currentAboutTrialsArticle == null){
            return new ResponseEntity(new CustomErrorType(
                    "Unable to upate. AboutTrialsArticle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        aboutTrialsArticleRepository.save(currentAboutTrialsArticle);
        return new ResponseEntity<>(currentAboutTrialsArticle, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addAboutTrialsArticle(@RequestBody AboutTrialsArticle aboutTrialsArticle){


        aboutTrialsArticleRepository.save(aboutTrialsArticle);
        return new ResponseEntity<>(aboutTrialsArticle, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delAboutTrialsArticle(@PathVariable("id") Integer id) {
        AboutTrialsArticle aboutTrialsArticle = aboutTrialsArticleRepository.findOne(id);
        if (aboutTrialsArticle == null ){
            return new ResponseEntity(new CustomErrorType(
                    "AboutTrialsArticle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            aboutTrialsArticleRepository.delete(aboutTrialsArticle);
        } catch (Exception e){
            return new ResponseEntity(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
