package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Comment;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.CommentRepository;


/**
 * Created by Igor on 08-Aug-16.
 */

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {
    @Autowired
    CommentRepository commentRepository;
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null ){
            return new ResponseEntity(new CustomErrorType(
                    "Comment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            commentRepository.delete(comment);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
