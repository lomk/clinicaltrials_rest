package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.clinicaltrials.domain.Comment;
import ua.com.clinicaltrials.services.CommentService;

import java.util.Calendar;

/**
 * Created by Igor on 26-Jul-16.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newComment(Comment comment){
        comment.setDateTimeField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        commentService.saveComment(comment);
        return "redirect:/article/" + comment.getArticle().getId();
    }
}
