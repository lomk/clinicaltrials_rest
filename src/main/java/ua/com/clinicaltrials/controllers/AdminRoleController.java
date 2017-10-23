package ua.com.clinicaltrials.controllers;

import org.springframework.stereotype.Controller;
import ua.com.clinicaltrials.domain.Role;
import ua.com.clinicaltrials.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Igor on 18-Jul-16.
 */
@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.listAllRoles());
        return "/admin/roles";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public String showRole(@PathVariable Integer id, Model model, HttpServletResponse response) {
        model.addAttribute("role", roleService.getRoleById(id));
        return "/admin/role";
    }

    @RequestMapping("/edit/{id}")
    public String editRole(@PathVariable Integer id, Model model){
        model.addAttribute("role", roleService.getRoleById(id));
        return "/admin/newrole";
    }

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newRole(Model model){
        model.addAttribute("role", new Role());
        return "/admin/newrole";
    }

    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String newRole(@ModelAttribute("role") Role roleForm, Model model){
        roleService.save(roleForm);
        return "/admin/roles";
    }

    @RequestMapping("delete/{id}")
    public String deleteRole(@PathVariable Integer id){
        roleService.deleteRole(id);
        return "redirect:/admin/role/all";
    }
}
