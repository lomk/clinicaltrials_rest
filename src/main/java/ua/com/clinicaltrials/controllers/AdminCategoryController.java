package ua.com.clinicaltrials.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

/**
 * Created by Igor on 18-Jul-16.
 */
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Category category){
        Category currentCategory = categoryRepository.findOne(id);
        if (currentCategory == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentCategory.setNameUa(category.getNameUa());
        currentCategory.setNameEn(category.getNameEn());
        currentCategory.setNameRu(category.getNameRu());
        currentCategory.setUrl(category.getUrl());
        categoryRepository.save(currentCategory);
        return new ResponseEntity<>(currentCategory, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Category category){

        if (category == null){
            return new ResponseEntity<>(new CustomErrorType("No category"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (    category.getNameUa() == null ||
                category.getNameEn() == null ||
                category.getNameRu() == null ||
                category.getNameUa().isEmpty() ||
                category.getNameEn().isEmpty() ||
                category.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No category name"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (categoryRepository.findByUrl(category.getUrl()) != null || !category.getUrl().isEmpty()){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A category with URL " +
                    category.getUrl() + " already exist."),HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Category category = categoryRepository.findOne(id);
        if (category == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            categoryRepository.delete(category);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
