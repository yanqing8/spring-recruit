package cn.atcat.service;

import cn.atcat.pojo.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> getCategoryList();

    Category getCategoryById(Integer id);

    void updateCategory(Category category);

    void deleteCategory(Integer id);
}
