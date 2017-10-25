package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Tag;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.TagRepository;


/**
 * Created by Igor on 22-Jul-16.
 */

@RestController
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Autowired
    TagRepository tagRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Tag tag){
        Tag currentTag = tagRepository.findOne(id);
        if (currentTag == null){
            return new ResponseEntity(new CustomErrorType(
                    "Unable to upate. Tag with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentTag.setNameUa(tag.getNameUa());
        currentTag.setNameEn(tag.getNameEn());
        currentTag.setNameRu(tag.getNameRu());
        currentTag.setUrl(tag.getUrl());
        tagRepository.save(currentTag);
        return new ResponseEntity<>(currentTag, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Tag tag){

        if (tag == null){
            return new ResponseEntity(new CustomErrorType("No tag"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (    tag.getNameUa() == null ||
                tag.getNameEn() == null ||
                tag.getNameRu() == null ||
                tag.getNameUa().isEmpty() ||
                tag.getNameEn().isEmpty() ||
                tag.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No tag name"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (tagRepository.findByUrl(tag.getUrl()) != null || !tag.getUrl().isEmpty()){
            return new ResponseEntity(new CustomErrorType("Unable to create. A tag with URL " +
                    tag.getUrl() + " already exist."),HttpStatus.CONFLICT);
        }
        tagRepository.save(tag);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Tag tag = tagRepository.findOne(id);
        if (tag == null ){
            return new ResponseEntity(new CustomErrorType(
                    "Tag with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            tagRepository.delete(tag);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    


}
