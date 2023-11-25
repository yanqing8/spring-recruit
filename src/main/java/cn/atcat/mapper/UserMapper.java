package cn.atcat.mapper;

import cn.atcat.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("insert into user(username,password,role,create_time,update_time)" +
            " values(#{username},#{md5String},#{role},now(),now())")
    void add(String username, String md5String, Integer role);
}
