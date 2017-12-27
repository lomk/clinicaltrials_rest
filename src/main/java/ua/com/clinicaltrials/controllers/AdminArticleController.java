package ua.com.clinicaltrials.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ArticleRepository;
import ua.com.clinicaltrials.repositories.UserRepository;
import ua.com.clinicaltrials.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Igor on 18-Jul-16.
 */
@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

//    @RequestMapping(value = "all/{page}", method = RequestMethod.GET)
//    public ResponseEntity<?> articleList(@PathVariable Integer page) {
//
//        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "dateField"));
//        Pageable pageable = new PageRequest(page, 9);
//
//        List<Article> articleList = (List<Article>) articleRepository.findAll(pageable);
//
//        if (articleList == null || articleList.isEmpty()){
//            return new ResponseEntity(new CustomErrorType("No data found"),
//                    HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(articleList, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getArticle(@PathVariable Integer id){
//        Article article = articleRepository.findOne(id);
//        if (article == null){
//            return new ResponseEntity(new CustomErrorType(
//                    "Article with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(article, HttpStatus.OK);
//    }


    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addArticle(@RequestBody Article article){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        article.setUser(user);

        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        articleRepository.save(article);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delArticle(@PathVariable("id") Integer id) {
        Article article = articleRepository.findOne(id);
        if (article == null ){
            return new ResponseEntity(new CustomErrorType(
                    "Local IP with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            articleRepository.delete(article);
        } catch (Exception e){
            return new ResponseEntity(new CustomErrorType(
                    "Can't delete."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }
}
