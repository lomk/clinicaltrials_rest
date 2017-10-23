package ua.com.clinicaltrials.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.Category;

import ua.com.clinicaltrials.domain.Tag;
import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.repositories.ArticleRepository;

import java.util.Collection;


/**
 * Created by Igor on 20-Jul-16.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Iterable<Article> listAllArticles() {
        return articleRepository.findAllByOrderByDateFieldDesc();
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepository.findOne(id);
    }

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleRepository.delete(id);
    }

    @Override
    public Iterable<Article> findByCategory(Category category) {
        return articleRepository.findByCategoryOrderByDateFieldAsc(category);
    }

    @Override
    public Iterable<Article> findByUser(User user) {
        return articleRepository.findByUserOrderByDateFieldAsc(user.getId());
    }

    @Override
    public Iterable<Article> findByTags(Collection<Tag> tags) {
        return articleRepository.findByTags(tags);
    }

}
