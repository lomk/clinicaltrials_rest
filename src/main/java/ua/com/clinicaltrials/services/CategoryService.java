package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Category;

/**
 * Created by Igor on 07-Jul-16.
 */
public interface CategoryService {
    Iterable<Category> listAllCategories();

    Category getCategoryById(Integer id);

    void saveCategory(Category category);

    void deleteCategory(Integer id);

    Category findByUrl(String url);
}
