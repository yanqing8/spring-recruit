package cn.atcat.mapper;

import cn.atcat.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name,create_user,create_time,update_time) values(#{categoryName},#{createUser},#{createTime},#{updateTime})")
    void addCategory(Category category);

    // 查询当前已登录用户和管理员创建的所有职业分类
    //(
    //  SELECT c.*
    //  FROM category c
    //  JOIN user u ON c.create_user = u.id
    //  WHERE u.role = 0
    //)
    //UNION
    //(
    //  SELECT c.*
    //  FROM category c
    //  JOIN user u ON c.create_user = u.id
    //  WHERE u.id = 7
    //)
    @Select("SELECT c.* FROM category c JOIN user u ON c.create_user = u.id WHERE u.role = 0 " +
            "UNION " +
            "(SELECT c.* FROM category c JOIN user u ON c.create_user = u.id WHERE u.id = #{id})")
    List<Category> getCategoryList(Integer id);

    @Select("select * from category where id = #{id}")
    Category getCategoryById(Integer id);

    @Update("update category set category_name=#{categoryName},update_time=#{updateTime} where id=#{id}")
    void updateCategory(Category category);

    @Delete("delete from category where id=#{id}")
    void deleteCategory(Integer id);
}
