package ua.com.clinicaltrials.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.Comment;
import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.repositories.CommentRepository;

/**
 * Created by Igor on 20-Jul-16.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Iterable<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.delete(id);
    }

    @Override
    public Iterable<Comment> findByArticle(Article article) {
        return commentRepository.findByArticle(article.getId());
    }

    @Override
    public Iterable<Comment> findByUser(User user) {
        return commentRepository.findByUser(user.getId());
    }
}
