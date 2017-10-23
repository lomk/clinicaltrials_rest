package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Tag;
import ua.com.clinicaltrials.services.TagService;

/**
 * Created by Igor on 22-Jul-16.
 */

@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "all")
    public String categoryList(Model model){
    model.addAttribute("tags", tagService.listAllTags());
    return "admin/tags";
}

    @RequestMapping(value = "new")
    public String newT(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/newtag";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String saveArticle( Tag tag){
        tagService.saveTag(tag);
        return "redirect:/admin/tag/all/";
    }

    @RequestMapping("edit/{id}")
    public String articleEdit(@PathVariable Integer id, Model model){
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/newtag";
    }

    @RequestMapping(value = "delete/{id}")
    public String deleteCategory(@PathVariable Integer id, Model model){
        tagService.deleteTag(id);
        return "redirect:/admin/tag/all";
    }

}
