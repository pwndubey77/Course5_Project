package org.upgrad.services;

import org.upgrad.models.Category;
import org.upgrad.models.UserProfile;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void createCategory(String title,String description);
    Category getCategory(int categoryId);
    List<Category> getAllCategories();
    Long checkCategory(int categoryId);
}
