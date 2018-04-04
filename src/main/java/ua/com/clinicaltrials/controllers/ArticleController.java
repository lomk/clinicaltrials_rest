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
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ArticleRepository;
import ua.com.clinicaltrials.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public ResponseEntity<?> articleList(
            @RequestParam(value = "search", required = false) Optional<String> search,
            @RequestParam(value = "id", required = false) Optional<Integer> id,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "lang", required = false) Optional<String> lang,
            @RequestParam(value = "tag", required = false) Optional<List<String>> tags,
            @RequestParam(value = "category", required = false) Optional<String> category
    ) {

        if (page.isPresent()
                && !id.isPresent()
                && !search.isPresent()
                && !tags.isPresent()
                && !category.isPresent()) {

            Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "dateField"));
            Pageable pageable = new PageRequest(page.get(), 9, sort);
            Page<Article> articlePage = articleRepository.findAll(pageable);
            List<Article> articleList = articlePage.getContent();

            if (articleList == null){
                return new ResponseEntity<>(new CustomErrorType("No data found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        }

        if (id.isPresent()
                && !id.get().toString().isEmpty()
                && !page.isPresent()
                && !search.isPresent()
                && !category.isPresent()) {

            Article article = articleRepository.findOne(id.get());
            if (article == null){
                return new ResponseEntity<>(new CustomErrorType(
                        "article with id " + id.get() + " not found."),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(article, HttpStatus.OK);
        }

        if (category.isPresent()
                && page.isPresent()
                && !category.get().toString().isEmpty()
                && !search.isPresent()
                && !id.isPresent()) {

            Category cat = categoryRepository.findByUrl(category.get());
            Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "dateField"));
            Pageable pageable = new PageRequest(page.get(), 19, sort);

            List<Article> articles = articleRepository.findByCategory(cat, pageable);
            if (articles == null){
                return new ResponseEntity<>(new CustomErrorType(
                        "article with category " + category.get() + " not found."),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(articles, HttpStatus.OK);
        }




        return new ResponseEntity<>(new CustomErrorType(
                "Bad parameters"),
                HttpStatus.NOT_ACCEPTABLE);
    }
}
