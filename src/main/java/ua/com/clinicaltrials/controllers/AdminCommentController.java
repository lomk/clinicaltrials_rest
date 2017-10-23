package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.clinicaltrials.services.CommentService;


/**
 * Created by Igor on 08-Aug-16.
 */
@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Integer id, @RequestHeader(value = "referer", required = false) final String referer){
        commentService.deleteComment(id);
        return "redirect:"+ referer;
    }
}
