package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.clinicaltrials.domain.Comment;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CommentRepository;

import java.util.Calendar;

/**
 * Created by Igor on 26-Jul-16.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> addComment(Comment comment){
        comment.setDateTimeField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        commentRepository.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable("id") Integer id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null){
            return new ResponseEntity(new CustomErrorType(
                    "Comment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
