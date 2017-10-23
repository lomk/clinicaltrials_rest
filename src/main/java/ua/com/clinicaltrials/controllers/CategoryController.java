package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.services.ArticleService;
import ua.com.clinicaltrials.services.CategoryService;
import ua.com.clinicaltrials.services.TagService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 01-Aug-16.
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
//    @Autowired
//    private ArticleService articleService;
    @Autowired
    private TagService tagService;


    @RequestMapping(value = "all/{page}", method = RequestMethod.GET)
    public String listAll(@PathVariable Integer page, Model model){
        List<Category> categories = (ArrayList) categoryService.listAllCategories();
        categories.subList(page-1, page+9);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        model.addAttribute("pages", categories.size()/10);
        return "categories";
    }

}
