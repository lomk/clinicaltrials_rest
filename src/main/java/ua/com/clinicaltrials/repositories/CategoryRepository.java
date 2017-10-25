package ua.com.clinicaltrials.repositories;

import ua.com.clinicaltrials.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

/**
 * Created by Igor on 07-Jul-16.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByUrl(String url);
}
