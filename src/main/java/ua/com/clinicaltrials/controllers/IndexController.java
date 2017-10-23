package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Igor on 09-Jun-16.
 */
@Controller
public class IndexController {
    @Autowired
    MenuService menuService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage(Locale locale, Model model) {
        List<Article> articles = (ArrayList<Article>) articleService.listAllArticles();
        if (articles.size() >= 10) {
            articles = articles.subList(0, 10);
        }
        model.addAttribute("articles", articles);
        model.addAttribute("menues", menuService.listAllMenues());
        return new ModelAndView("index");
    }
}
