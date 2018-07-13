package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

import java.util.Set;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }


    @Override
    public void createCategory(int id , String title, String description) {

        categoryRepository.addCategoryValues (id,title, description);
    }

    @Override
    public Category getCategory(int categoryId) {
        return categoryRepository.getCategory (categoryId);
    }

   /* @Override
    public Set<Integer> getQuestionByCategory(int categoryId) {
        return categoryRepository.getQuestionsByCategoryId(categoryId);
    }
    */

}

