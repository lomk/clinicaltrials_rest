package ua.com.clinicaltrials.repositories;

import ua.com.clinicaltrials.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Igor on 07-Jul-16.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByUrl(String url);

    List<Category> findAllByNameEnLike(String search);
    List<Category> findAllByNameRuLike(String search);
    List<Category> findAllByNameUaLike(String search);


    List<Category> findAllByNameEnContains(String search);
    List<Category> findAllByNameRuContains(String search);
    List<Category> findAllByNameUaContains(String search);
}
