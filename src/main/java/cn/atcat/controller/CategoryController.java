package cn.atcat.controller;

import cn.atcat.pojo.Category;
import cn.atcat.pojo.Result;
import cn.atcat.service.CategoryService;
import cn.atcat.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @GetMapping("/list")
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
        // 通过分类id查询该分类记录
        Category c = categoryService.getCategoryById(category.getId());
        // 获取用户id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Integer role = (Integer) map.get("role");

        // 判断该分类记录是否是当前用户创建的，假设该用户是管理员，依旧可以执行操作
        if (!Objects.equals(c.getCreateUser(), userId) && role != 0) {
            return Result.error("无权限修改");
        }

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
