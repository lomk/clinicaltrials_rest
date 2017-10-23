package ua.com.clinicaltrials.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.ArticleRepository;
import ua.com.clinicaltrials.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Igor on 18-Jul-16.
 */
@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> articleList() {
        List<Article> articleList = (List) articleService.listAllArticles();
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

    @RequestMapping(value = "add", headers=("content-type=multipart/*"), method = RequestMethod.POST)
    public String saveArticle( Article article,
                               BindingResult bindingResult,
                               @RequestParam(value = "image",
                                       required = false) MultipartFile image){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        //article.setDesc(article.getBody().substring(0, 99));
        article.setUser(userService.findByUsername(user.getUsername()));
        article.setRating((long) 0);
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        String imageName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        imageName = imageName + ".jpg";
        if (!image.isEmpty()) {
            try {
                imageService.validateImage(image);

            } catch (RuntimeException re) {
                bindingResult.reject(re.getMessage());
                return "redirect:/admin/article/new";
            }
            try {
                article.setImgUrl(imageName);
                imageService.saveImage(imageName, image);

            } catch (IOException e) {
                bindingResult.reject(e.getMessage());
                return "redirect:/admin/article/new";
            }
        }

        articleService.saveArticle(article);
        return "redirect:/admin/article/" + article.getId();
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
