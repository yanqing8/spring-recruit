package cn.atcat.service;

import cn.atcat.pojo.PageBean;
import cn.atcat.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);

    void register(String username, String password, Integer role);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);

    PageBean<User> userList(Integer pageNum, Integer pageSize, Integer role, String otherParam);

    void delete(List<Integer> ids);
    void add(User user);

    User findUserById(Integer id);

    void resetPwd(Integer id);
}
