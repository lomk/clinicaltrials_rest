package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.clinicaltrials.domain.Article;
import ua.com.clinicaltrials.domain.Category;

import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Igor on 19-Jul-16.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

//    @Query(value = "SELECT a FROM Article a INNER JOIN a.tags c WHERE c IN (:tags) ORDER BY a.dateField")
//    List<Article> findByTags(@Param("tags")Collection<Tag> tags);

    Article findByUrl(String url);
    List<Article> findByCategory(Category category, Pageable pageable);
    List<Article> findByCategoryOrderByDateFieldAsc(Category category);

    List<Article> findByUserOrderByDateFieldAsc(Integer userId);
    List<Article> findAllByOrderByDateFieldDesc();

    List<Article> findAllByTitleRuIsContainingOrderByDateField(String search);
    List<Article> findAllByTitleUaIsContainingOrderByDateField(String search);
    List<Article> findAllByTitleEnIsContainingOrderByDateField(String search);

}
