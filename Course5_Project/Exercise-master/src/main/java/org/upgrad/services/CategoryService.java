package org.upgrad.services;

import org.upgrad.models.Category;
import org.upgrad.models.UserProfile;

import java.util.Date;
import java.util.Set;

public interface CategoryService {
    void createCategory(int id,String title,String description);
    Category getCategory(int categoryId);
    //Set<Integer> getQuestionByCategory(int categoryId);
}
