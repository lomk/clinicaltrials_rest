package ua.com.clinicaltrials.controllers;

import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Igor on 18-Jul-16.
 */
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "all")
    public String categoryList(Model model){
        model.addAttribute("categories", categoryService.listAllCategories());
        return "admin/categories";
    }

    @RequestMapping(value = "new")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());
        return "admin/newcategory";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String saveArticle( Category category){
       categoryService.saveCategory(category);
        return "redirect:/admin/category/all/";
    }

    @RequestMapping("edit/{id}")
    public String articleEdit(@PathVariable Integer id, Model model){
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/newcategory";
    }

    @RequestMapping(value = "delete/{id}")
    public String deleteCategory(@PathVariable Integer id, Model model){
        categoryService.deleteCategory(id);
        return "redirect:/admin/category/all";
    }
}
