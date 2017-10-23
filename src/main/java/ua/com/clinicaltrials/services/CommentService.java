package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.Comment;
import ua.com.clinicaltrials.domain.User;

/**
 * Created by Igor on 20-Jul-16.
 */
public interface CommentService {
    Iterable<Comment> listAllComments();

    Comment getCommentById(Integer id);

    void saveComment(Comment comment);

    void deleteComment(Integer id);

    Iterable<Comment> findByArticle(Article article);

    Iterable<Comment> findByUser(User user);
}
