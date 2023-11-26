package cn.atcat.controller;

import cn.atcat.pojo.Category;
import cn.atcat.pojo.Result;
import cn.atcat.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 新增分类
    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        log.info("name:{}", category.getCategoryName());
        categoryService.addCategory(category);
        return Result.success();
    }

    // 查询当前已登录用户和管理员创建的所有职业分类
    @GetMapping
    public Result<List<Category>> getCategoryList() {
        log.info("查询分类列表");
        List<Category> list = categoryService.getCategoryList();
        return Result.success(list);
    }

    // 查询分类详情
    @GetMapping("/detail")
    public Result<Category> getCategoryById(Integer id) {
        log.info("查询分类详情：{}", id);
        Category category = categoryService.getCategoryById(id);
        return Result.success(category);
    }

    // 更新分类
    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        log.info("更新分类：{}", category);
        categoryService.updateCategory(category);
        return Result.success();
    }

    // 删除分类
    @DeleteMapping
    public Result deleteCategory(Integer id) {
        log.info("删除分类：{}", id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

}
