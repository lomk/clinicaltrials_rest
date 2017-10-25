package ua.com.clinicaltrials.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.com.clinicaltrials.domain.*;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ArticleRepository;
import ua.com.clinicaltrials.repositories.CategoryRepository;
import ua.com.clinicaltrials.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 13-Jul-16.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    TagRepository tagRepository;

    @RequestMapping(value = "all/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> articleList(@PathVariable Integer page) {

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "dateField"));
        Pageable pageable = new PageRequest(page, 9, sort);

        List<Article> articleList = (List<Article>) articleRepository.findAll(pageable);

        if (articleList == null || articleList.isEmpty()){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticle(@PathVariable Integer id){
        Article article = articleRepository.findOne(id);
        if (article == null){
            return new ResponseEntity(new CustomErrorType(
                    "Article with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/{categoryUrl}/{page}", method = RequestMethod.GET)
    public ResponseEntity<?> articleList(@PathVariable Integer page, @PathVariable String categoryUrl) {

        Category category = categoryRepository.findByUrl(categoryUrl);

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "dateField"));
        Pageable pageable = new PageRequest(page, 9, sort);

        List<Article> articleList = articleRepository.findByCategory(category, pageable);

        if (articleList == null || articleList.isEmpty()){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/{tagUrl}")
    public ResponseEntity showArticlesByTag(@PathVariable String tagUrl, Model model){
        Tag tag = tagRepository.findByUrl(tagUrl);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        List<Article> articleList = articleRepository.findByTags(tags);

        if (articleList == null || articleList.isEmpty() || articleList.size() < 1){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

}
