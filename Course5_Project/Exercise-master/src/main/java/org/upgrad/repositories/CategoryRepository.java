package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into category (title,description) values (?1,?2)")
    void addCategoryValues(String title,String description);

    @Query(nativeQuery = true, value="select * from category where id=?1")
    Category getCategory(int categoryId);

    @Query(nativeQuery = true, value="select * from category")
    List<Category> getAllCategories();


}

