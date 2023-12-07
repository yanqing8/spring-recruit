package cn.atcat.mapper;

import cn.atcat.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name,create_user,create_time,update_time) values(#{categoryName},#{createUser},#{createTime},#{updateTime})")
    void addCategory(Category category);

    // 查询当前已登录用户和管理员创建的所有职业分类
    //SELECT c.*
    //FROM category c
    //JOIN user u ON c.create_user = u.id
    //WHERE u.id = 6 OR u.role = 0
    //ORDER BY
    //  CASE WHEN u.role = 0 THEN 1 ELSE 0 END, -- 将管理员的记录放在最后
    //  c.update_time DESC;
    List<Category> getCategoryList(Integer id);

    @Select("select * from category where id = #{id}")
    Category getCategoryById(Integer id);

    @Update("update category set category_name=#{categoryName},update_time=#{updateTime} where id=#{id}")
    void updateCategory(Category category);

    @Delete("delete from category where id=#{id}")
    void deleteCategory(Integer id);

    void deleteCategoryByUserId(List<Integer> ids);
}
