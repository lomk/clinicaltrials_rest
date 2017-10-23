package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Category;
import ua.com.clinicaltrials.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Igor on 07-Jul-16.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category) ;
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category findByUrl(String url) {
        return categoryRepository.findByUrl(url);
    }
}
