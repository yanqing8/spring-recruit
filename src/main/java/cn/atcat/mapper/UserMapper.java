package cn.atcat.mapper;

import cn.atcat.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("insert into user(username,password,nickname, email, role,create_time,update_time) values(#{username},#{password},#{nickname},#{email}, #{role},#{createTime},#{updateTime})")
    void add(User user);
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
    List<User> userList(Integer role, String otherParam);

    void delete(List<Integer> ids);

    @Update("update user set level = #{lv} where id = #{userId}")
    void updateLv(Integer userId, Integer lv);

    @Select("select * from user where id = #{id}")
    User findUserById(Integer id);
}
