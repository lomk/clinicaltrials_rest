package ua.com.clinicaltrials.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Igor on 04-Jul-16.
 */
@Controller
public class AdminMainController {
    @RequestMapping("/admin")
    public String admin(Model model){
        return "admin/admin";
    }
}
