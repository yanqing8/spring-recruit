package cn.atcat.service.impl;

import cn.atcat.mapper.CategoryMapper;
import cn.atcat.pojo.Category;
import cn.atcat.service.CategoryService;
import cn.atcat.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void addCategory(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.addCategory(category);
    }

    @Override
    public List<Category> getCategoryList() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = null;
        if(map != null && map.get("id") != null){
            id = (Integer) map.get("id");
        }
        List<Category> list = categoryMapper.getCategoryList(id);
        return list;
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = categoryMapper.getCategoryById(id);
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }
}
