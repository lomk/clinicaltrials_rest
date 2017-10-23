package ua.com.clinicaltrials.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Igor on 18-Jul-16.
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

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
    public String articleList(Model model) {
        model.addAttribute("articles", articleService.listAllArticles());
        return "admin/articles";
    }

    @RequestMapping("edit/{id}")
    public String articleEdit(@PathVariable Integer id, Model model){
        model.addAttribute("article", articleService.getArticleById(id));
        return "admin/newarticle";
    }

    @RequestMapping("new")
    public String newArticle(Model model){
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("tags", tagService.listAllTags());
        model.addAttribute("article", new Article());
        return "admin/newarticle";
    }

    @RequestMapping("{id}")
    public String showArticle(@PathVariable Integer id, Model model){
        model.addAttribute("article", articleService.getArticleById(id));
        return "admin/article";
    }

    @RequestMapping(value = "new", headers=("content-type=multipart/*"), method = RequestMethod.POST)
    public String saveArticle( Article article, BindingResult bindingResult, @RequestParam(value = "image", required = false) MultipartFile image){
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

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        articleService.deleteArticle(id);
        return "redirect:/admin/article/all";
    }
}
