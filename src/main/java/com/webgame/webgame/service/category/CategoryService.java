package com.webgame.webgame.service.category;

import com.webgame.webgame.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategoryList();
    Category getCategoryById(Long id);
}
