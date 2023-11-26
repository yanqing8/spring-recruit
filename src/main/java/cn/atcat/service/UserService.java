package cn.atcat.service;

import cn.atcat.pojo.User;

public interface UserService {
    User findUserByUsername(String username);

    void register(String username, String password, Integer role);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);

    void pay(Integer lv);
}
