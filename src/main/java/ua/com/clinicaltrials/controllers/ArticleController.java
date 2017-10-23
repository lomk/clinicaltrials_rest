package ua.com.clinicaltrials.controllers;

import ua.com.clinicaltrials.domain.*;
import ua.com.clinicaltrials.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.services.CategoryService;
import ua.com.clinicaltrials.services.TagService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 13-Jul-16.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    TagService tagService;
    
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public String listAll(Model model) {
//        model.addAttribute("tags", tagService.listAllTags());
//        model.addAttribute("articles", articleService.listAllArticles());
//        return "articles";
//    }
    @RequestMapping(value = "/all/page/{page}", method = RequestMethod.GET)
    public String listArticles(@PathVariable Integer page, Model model) {
        List<Article> articles = (ArrayList<Article>) articleService.listAllArticles();

        int pages = ((int) Math.ceil(((double) articles.size())/10));

        if (articles.size() >= page*10) {
                articles = articles.subList((page * 10 - 10), (page * 10));
        }
        if (page != 1 && articles.size() >= page*10-9){
            articles = articles.subList((page * 10 - 10), articles.size());
        }

        model.addAttribute("page", page);
        model.addAttribute("tags", tagService.listAllTags());
        model.addAttribute("articles", articles);
        model.addAttribute("pages", pages);
        return "articles";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public String showArticle(@PathVariable Integer id, Model model, HttpServletResponse response) {
        Article article = articleService.getArticleById(id);
        User user = article.getUser();
        model.addAttribute("tags", tagService.listAllTags());
        model.addAttribute("article", article);
        model.addAttribute("user", user);
        model.addAttribute("comment", new Comment());
        return "article";
    }

    @RequestMapping(value = "/category/{categoryUrl}")
    public String showArticlesByCategory(@PathVariable String categoryUrl, Model model){
        Category category = categoryService.findByUrl(categoryUrl);
        model.addAttribute("category", category);
        model.addAttribute("articles", articleService.findByCategory(category));
        model.addAttribute("tags", tagService.listAllTags());
        return "articles";
    }

    @RequestMapping(value = "/{tagUrl}")
    public String showArticlesByTag(@PathVariable String tagUrl, Model model){
        Tag tag = tagService.findByUrl(tagUrl);
        model.addAttribute("tag", tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        model.addAttribute("articles", articleService.findByTags(tags));
        model.addAttribute("tags", tagService.listAllTags());
        return "articles";
    }

}
