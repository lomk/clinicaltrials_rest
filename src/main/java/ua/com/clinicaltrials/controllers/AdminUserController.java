package ua.com.clinicaltrials.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.clinicaltrials.domain.Role;
import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.services.RoleService;
import ua.com.clinicaltrials.services.UserService;
import ua.com.clinicaltrials.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Igor on 18-Jul-16.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "/admin/users";
    }

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public String showUser(@PathVariable Integer id, Model model, HttpServletResponse response) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/user";
    }

    @RequestMapping(value = "edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRole());
        return "/admin/user";
    }

    @RequestMapping(value = "new", method=RequestMethod.GET)
    public String newUser(Model model){
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.addAll((ArrayList<Role>)roleService.listAllRoles());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "admin/newuser";
    }

    @RequestMapping(value = "new", method=RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, @RequestParam(value = "image", required = false) MultipartFile image) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/newuser";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/delete/{id}")
    public String userDelete(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
