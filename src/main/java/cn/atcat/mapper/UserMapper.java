package cn.atcat.mapper;

import cn.atcat.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("insert into user(username,password,role,create_time,update_time)" +
            " values(#{username},#{md5String},#{role},now(),now())")
    void add(String username, String md5String, Integer role);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);

    @Update("update user set level=level+#{lv},update_time=now() where id=#{id}")
    void updateLv(Integer lv, Integer id);
}
