package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CategoryRepository;

import java.util.List;

public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList == null){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id) {
        Category category = categoryRepository.findOne(id);
        if (category == null){
            return new ResponseEntity(new CustomErrorType(
                    "Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
