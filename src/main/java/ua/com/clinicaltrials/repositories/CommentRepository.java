package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Comment;

import java.util.List;

/**
 * Created by Igor on 19-Jul-16.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByUser(Integer userId);
    List<Comment> findByArticle(Integer articleId);
}
