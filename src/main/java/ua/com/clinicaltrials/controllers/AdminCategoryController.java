package ua.com.clinicaltrials.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CategoryRepository;
import ua.com.clinicaltrials.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Igor on 18-Jul-16.
 */
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> listCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList == null){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer id) {
        Category category = categoryRepository.findOne(id);
        if (category == null){
            return new ResponseEntity(new CustomErrorType(
                    "Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category){
        Category currentCategory = categoryRepository.findOne(id);
        if (currentCategory == null){
            return new ResponseEntity(new CustomErrorType(
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
    public ResponseEntity<?> addCategory(@RequestBody Category category){

        if (category == null){
            return new ResponseEntity(new CustomErrorType("No category"),HttpStatus.NOT_ACCEPTABLE);
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
            return new ResponseEntity(new CustomErrorType("Unable to create. A category with URL " +
                    category.getUrl() + " already exist."),HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delCategory(@PathVariable("id") Integer id) {
        Category category = categoryRepository.findOne(id);
        if (category == null ){
            return new ResponseEntity(new CustomErrorType(
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
