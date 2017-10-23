package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.domain.Tag;
import ua.com.clinicaltrials.domain.User;

import java.util.Collection;

/**
 * Created by Igor on 20-Jul-16.
 */
public interface ArticleService {
    Iterable<Article> listAllArticles();

    Article getArticleById(Integer id);

    void saveArticle(Article article);

    void deleteArticle(Integer id);

    Iterable<Article> findByCategory(Category category);

    Iterable<Article> findByUser(User user);

    Iterable<Article> findByTags(Collection<Tag> tag);

}
