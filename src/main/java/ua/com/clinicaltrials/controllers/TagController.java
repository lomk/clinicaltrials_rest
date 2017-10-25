package ua.com.clinicaltrials.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.Tag;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> tagList() {

        List<Tag> tagList = tagRepository.findAll();

        if (tagList == null || tagList.isEmpty()){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTag(@PathVariable Integer id){
        Tag tag = tagRepository.findOne(id);
        if (tag == null){
            return new ResponseEntity(new CustomErrorType(
                    "Tag with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
