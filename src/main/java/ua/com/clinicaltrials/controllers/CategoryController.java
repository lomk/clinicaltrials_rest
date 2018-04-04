package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;


@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "search", required = false) Optional<String> search,
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "lang", required = false) Optional<String> lang
    ) {

        if (search.isPresent()
                && page.isPresent()
                && lang.isPresent()
                && !id.isPresent()) {
            if (lang.get().equals("ru")){
                List<Category> categoryList = categoryRepository.findAllByNameRuContains(search.get());
                if (categoryList == null){
                    return new ResponseEntity<>(new CustomErrorType("No data found"),
                            HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(categoryList, HttpStatus.OK);
            }
            if (lang.get().equals("ua")){
                List<Category> categoryList = categoryRepository.findAllByNameUaContains(search.get());
                if (categoryList == null){
                    return new ResponseEntity<>(new CustomErrorType("No data found"),
                            HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(categoryList, HttpStatus.OK);
            }
            if (lang.get().equals("en")){
                List<Category> categoryList = categoryRepository.findAllByNameEnContains(search.get());
                if (categoryList == null){
                    return new ResponseEntity<>(new CustomErrorType("No data found"),
                            HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(categoryList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Bad lang", HttpStatus.OK);
        }

        if (page.isPresent()
                && !id.isPresent()
                && !search.isPresent()) {

            List<Category> categoryList = categoryRepository.findAll();
            if (categoryList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }

        if (id.isPresent()
                && !id.get().toString().isEmpty()
                && !page.isPresent()
                && !search.isPresent()) {

            Category category = categoryRepository.findOne(id.get());
            if (category == null){
                try {
                    return new ResponseEntity<>(new CustomErrorType(
                            "Category with id " + id.get() + " not found."),
                            HttpStatus.NOT_FOUND);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(category, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
