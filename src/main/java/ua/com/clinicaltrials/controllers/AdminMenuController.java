package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.services.MenuService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Igor on 30-Sep-16.
 */

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String listMenues(Model model) {
        model.addAttribute("menues", menuService.listAllMenues());
        return "/admin/menues";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public String showMenu(@PathVariable Integer id, Model model, HttpServletResponse response) {
        model.addAttribute("menu", menuService.getMenuById(id));
        return "/admin/menu";
    }

    @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
    public String editMenu(@PathVariable Integer id, Model model){
        model.addAttribute("menu", menuService.getMenuById(id));
        return "/admin/newMenu";
    }

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newMenu(Model model){
        model.addAttribute("menu", new Menu());
        return "/admin/newMenu";
    }

    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String newMenu(@ModelAttribute("menu") Menu menuForm, Model model){
        menuService.save(menuForm);
        return "/admin/menues";
    }

    @RequestMapping("delete/{id}")
    public String deleteMenu(@PathVariable Integer id){
        menuService.deleteMenu(id);
        return "redirect:/admin/menu/all";
    }
}
