package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.domain.Section;
import ua.com.clinicaltrials.services.MenuService;
import ua.com.clinicaltrials.services.SectionService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Igor on 30-Sep-16.
 */
@Controller
@RequestMapping("/admin/section")
public class AdminSectionController {
    @Autowired
    SectionService sectionService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String listSections(Model model) {
        model.addAttribute("sections", sectionService.listAllSections());
        return "/admin/sections";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public String showSection(@PathVariable Integer id, Model model, HttpServletResponse response) {
        model.addAttribute("section", sectionService.getSectionById(id));
        return "/admin/section";
    }

    @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
    public String editSection(@PathVariable Integer id, Model model){
        ArrayList<Menu> menues = new ArrayList<Menu>();
        menues.addAll((ArrayList<Menu>)menuService.listAllMenues());
        model.addAttribute("menues", menues);
        model.addAttribute("section", sectionService.getSectionById(id));
        return "/admin/newSection";
    }

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newSection(Model model){
        ArrayList<Menu> menues = new ArrayList<Menu>();
        menues.addAll((ArrayList<Menu>)menuService.listAllMenues());
        model.addAttribute("section", new Section());
        model.addAttribute("menues", menues);
        return "/admin/newSection";
    }

    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String newSection(@ModelAttribute("section") Section sectionForm, Model model){

        sectionService.saveSection(sectionForm);
        return "/admin/sections";
    }

    @RequestMapping("delete/{id}")
    public String deleteSection(@PathVariable Integer id){
        sectionService.deleteSection(id);
        return "redirect:/admin/section/all";
    }
}
