package com.webgame.webgame.repository;

import com.webgame.webgame.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = """
    SELECT category_id, category_name, category_img
    FROM category c
   """, nativeQuery = true)
    List<Category> getAllCategoryList();
}
