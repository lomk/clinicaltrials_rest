package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.services.MenuService;
import ua.com.clinicaltrials.services.SectionService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Igor on 03-Oct-16.
 */

@Controller
@RequestMapping("/sections")
public class SectionController {
    @Autowired
    SectionService sectionService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/{url}", method= RequestMethod.GET)
    public String showSection(@PathVariable String url, Model model, HttpServletResponse response) {
        model.addAttribute("section", sectionService.findByUrl(url));
        model.addAttribute("menues", menuService.listAllMenues());
        return "/section";
    }
}
